package com.devas.IMS.Repos

import com.devas.IMS.Entities.Supplier
import org.springframework.data.jpa.repository.JpaRepository

interface SupplierRepository : JpaRepository<Supplier, Long> {
}