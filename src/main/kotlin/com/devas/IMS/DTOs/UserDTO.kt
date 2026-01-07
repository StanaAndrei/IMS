package com.devas.IMS.DTOs

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.devas.IMS.Enums.UserRole
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDTO(
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,

    @JsonIgnore
    var password: String? = null,

    var phoneNumber: String? = null,
    var role: UserRole? = null,
    var transactions: List<TransactionDTO>? = null,
    var createdAt: LocalDateTime? = null
)