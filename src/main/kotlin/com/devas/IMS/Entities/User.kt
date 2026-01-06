package com.devas.IMS.Entities

import com.devas.IMS.Enums.UserRole
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime



@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank(message = "Name is required")
    var name: String? = null

    @NotBlank(message = "Email is required")
    @Column(unique = true)
    var email: String? = null

    @NotBlank(message = "Password is required")
    var password: String? = null

    @NotBlank(message = "Phone Number is required")
    @Column(name = "phone_number")
    var phoneNumber: String? = null

    @Enumerated(EnumType.STRING)
    var role: UserRole? = null

    @OneToMany(mappedBy = "user")
    var transactions: MutableList<Transaction> = mutableListOf()

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    // Implemented toString based on your image
    override fun toString(): String {
        return "User{" +
                "id=$id" +
                ", name='$name'" +
                ", email='$email'" +
                ", password='$password'" +
                ", phoneNumber='$phoneNumber'" +
                ", role=$role" +
                ", createdAt=$createdAt" +
                "}"
    }
}