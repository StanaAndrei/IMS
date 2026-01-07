package com.devas.IMS.Repos

import com.devas.IMS.Entities.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}