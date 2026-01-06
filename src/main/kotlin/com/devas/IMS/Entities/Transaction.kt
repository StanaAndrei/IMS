package com.devas.IMS.Entities

import com.devas.IMS.Enums.TransactionStatus
import com.devas.IMS.Enums.TransactionType
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var totalProducts: Int? = null

    var totalPrice: BigDecimal? = null

    @Enumerated(EnumType.STRING)
    var transactionType: TransactionType? = null

    @Enumerated(EnumType.STRING)
    var status: TransactionStatus? = null

    var description: String? = null

    var updatedAt: LocalDateTime? = null

    @Column(updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    var supplier: Supplier? = null

    override fun toString(): String {
        return "Transaction{" +
                "id=$id" +
                ", totalProducts=$totalProducts" +
                ", totalPrice=$totalPrice" +
                ", transactionType=$transactionType" +
                ", status=$status" +
                ", description='$description'" +
                ", updatedAt=$updatedAt" +
                ", createdAt=$createdAt" +
                "}"
    }
}