package com.devas.IMS.Security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter(
    private val jwtUtils: JwtUtils,
    private val customUserDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {

    // Kotlin equivalent of @Slf4j
    private val log = LoggerFactory.getLogger(AuthFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = getTokenFromRequest(request)

            if (token != null) {
                val email = jwtUtils.getUsernameFromToken(token)

                // Check if email is valid before hitting the DB
                if (StringUtils.hasText(email)) {
                    val userDetails = customUserDetailsService.loadUserByUsername(email)

                    if (jwtUtils.isTokenValid(token, userDetails)) {
                        log.info("Token is valid, {}", email)

                        val authenticationToken = UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.authorities
                        )

                        authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authenticationToken
                    }
                }
            }

            filterChain.doFilter(request, response)

        } catch (e: Exception) {
            log.error("Error occurred in AuthFilter: {}", e.message)
            // We catch the error so the app doesn't crash, but the user remains unauthenticated
        }
    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val tokenWithBearer = request.getHeader("Authorization")

        if (StringUtils.hasText(tokenWithBearer) && tokenWithBearer.startsWith("Bearer ")) {
            return tokenWithBearer.substring(7)
        }
        return null
    }
}