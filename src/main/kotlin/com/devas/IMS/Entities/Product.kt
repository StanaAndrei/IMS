package com.devas.IMS.Entities

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "products")
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank(message = "Name is required")
    var name: String? = null

    @NotBlank(message = "Sku is required")
    @Column(unique = true)
    var sku: String? = null

    @Positive(message = "Product price must be a positive value")
    var price: BigDecimal? = null

    @Min(value = 0, message = "Stock quantity cannot be lesser than zero")
    var stockQuantity: Int? = null

    var description: String? = null

    var imageUrl: String? = null

    var expiryDate: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null

    @Column(updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category? = null

    override fun toString(): String {
        return "Product{" +
                "id=$id" +
                ", name='$name'" +
                ", sku='$sku'" +
                ", price=$price" +
                ", stockQuantity=$stockQuantity" +
                ", description='$description'" +
                ", imageUrl='$imageUrl'" +
                ", expiryDate=$expiryDate" +
                ", updatedAt=$updatedAt" +
                ", createdAt=$createdAt" +
                "}"
    }
}