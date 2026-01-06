package com.devas.IMS.Entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "suppliers")
class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank(message = "Name is required")
    var name: String? = null

    var address: String? = null
}