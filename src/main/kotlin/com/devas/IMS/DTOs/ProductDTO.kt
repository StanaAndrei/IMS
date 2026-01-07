package com.devas.IMS.DTOs

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductDTO(
    var id: Long? = null,
    var productId: Long? = null,
    var categoryId: Long? = null,
    var supplierId: Long? = null,
    var name: String? = null,
    var sku: String? = null,
    var price: BigDecimal? = null,
    var stockQuantity: Int? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var expiryDate: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var createdAt: LocalDateTime? = null
)