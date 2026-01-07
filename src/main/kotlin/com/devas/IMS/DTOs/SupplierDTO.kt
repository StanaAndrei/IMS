package com.devas.IMS.DTOs

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class SupplierDTO(
    var id: Long? = null,

    @field:NotBlank(message = "Name is required")
    var name: String? = null,

    var address: String? = null
)