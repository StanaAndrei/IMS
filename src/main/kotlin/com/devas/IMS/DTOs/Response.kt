package com.devas.IMS.DTOs

import com.fasterxml.jackson.annotation.JsonInclude
import com.devas.IMS.Enums.UserRole
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Response(
    // Generic
    var status: Int = 200, // Default to 200 or 0 as preferred
    var message: String? = null,

    // For Login
    var token: String? = null,
    var role: UserRole? = null,
    var expirationTime: String? = null,

    // For Pagination
    var totalPages: Int? = null,
    var totalElements: Long? = null,

    // Data Output (Optional)
    var user: UserDTO? = null,
    var users: List<UserDTO>? = null,

    var supplier: SupplierDTO? = null,
    var suppliers: List<SupplierDTO>? = null,

    var category: CategoryDTO? = null,
    var categories: List<CategoryDTO>? = null,

    var product: ProductDTO? = null,
    var products: List<ProductDTO>? = null,

    var transaction: TransactionDTO? = null,
    var transactions: List<TransactionDTO>? = null,

    val timestamp: LocalDateTime = LocalDateTime.now()
)