package com.devas.IMS.DTOs

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.validation.constraints.Positive

@JsonIgnoreProperties(ignoreUnknown = true)
data class TransactionRequest(
    @field:Positive(message = "Product id is required")
    var productId: Long? = null,

    @field:Positive(message = "Quantity is required")
    var quantity: Int? = null,

    var supplierId: Long? = null,
    var description: String? = null
)