package com.devas.IMS.Security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.Date
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Service
class JwtUtils {

    // Kotlin logger equivalent to @Slf4j
    private val log = LoggerFactory.getLogger(JwtUtils::class.java)

    companion object {
        // 6 months expiration
        private const val EXPIRATION_TIME_IN_MILLISEC = 100L * 60L * 60L * 24L * 30L * 6L
    }

    private lateinit var key: SecretKey

    @Value($$"${secreteJwtString}")
    private lateinit var secreteJwtString: String

    @PostConstruct
    private fun init() {
        val keyByte = secreteJwtString.toByteArray(StandardCharsets.UTF_8)
        this.key = SecretKeySpec(keyByte, "HmacSHA256")
    }

    fun generateToken(email: String): String {
        return Jwts.builder()
            .subject(email)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILLISEC))
            .signWith(key)
            .compact()
    }

    fun getUsernameFromToken(token: String): String {
        return extractClaims(token) { it.subject }
    }

    private fun <T> extractClaims(token: String, claimsResolver: (Claims) -> T): T {
        val claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
        return claimsResolver(claims)
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractClaims(token) { it.expiration }.before(Date())
    }
}