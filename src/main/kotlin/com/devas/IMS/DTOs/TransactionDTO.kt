package com.devas.IMS.DTOs

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.devas.IMS.Enums.TransactionStatus
import com.devas.IMS.Enums.TransactionType
import io.swagger.v3.oas.annotations.media.Schema // IMPORT THIS
import java.math.BigDecimal
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class TransactionDTO(
    var id: Long? = null,
    var totalProducts: Int? = null,
    var totalPrice: BigDecimal? = null,
    var transactionType: TransactionType? = null,
    var status: TransactionStatus? = null,
    var description: String? = null,
    var updatedAt: LocalDateTime? = null,
    var createdAt: LocalDateTime? = null,

    // FIX: Hide this from Swagger to stop the infinite loop/crash
    @Schema(hidden = true)
    var user: UserDTO? = null,

    var product: ProductDTO? = null,
    var supplier: SupplierDTO? = null
)