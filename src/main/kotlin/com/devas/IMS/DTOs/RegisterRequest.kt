package com.devas.IMS.DTOs

import com.devas.IMS.Enums.UserRole
import jakarta.validation.constraints.NotBlank

data class RegisterRequest(
    @field:NotBlank(message = "Name is required")
    var name: String? = null,

    @field:NotBlank(message = "Email is required")
    var email: String? = null,

    @field:NotBlank(message = "Password is required")
    var password: String? = null,

    @field:NotBlank(message = "PhoneNumber is required")
    var phoneNumber: String? = null,

    var role: UserRole? = null
)