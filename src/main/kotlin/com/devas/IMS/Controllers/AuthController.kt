package com.devas.IMS.Controllers

import com.devas.IMS.DTOs.LoginRequest
import com.devas.IMS.DTOs.RegisterRequest
import com.devas.IMS.DTOs.Response
import com.devas.IMS.Services.IUserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: IUserService
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody @Valid registerRequest: RegisterRequest): ResponseEntity<Response> {
        return ResponseEntity.ok(userService.registerUser(registerRequest))
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody @Valid loginRequest: LoginRequest): ResponseEntity<Response> {
        return ResponseEntity.ok(userService.loginUser(loginRequest))
    }
}