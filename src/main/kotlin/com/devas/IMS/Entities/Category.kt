package com.devas.IMS.Entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "categories")
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank(message = "Name is required")
    @Column(unique = true)
    var name: String? = null

    @OneToMany(mappedBy = "category")
    var products: MutableList<Product> = mutableListOf()

    override fun toString(): String {
        return "Category{" +
                "id=$id" +
                ", name='$name'" +
                "}"
    }
}