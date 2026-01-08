package com.devas.IMS.Services

import com.devas.IMS.DTOs.Response
import com.devas.IMS.DTOs.SupplierDTO

interface ISupplierService {
    fun addSupplier(supplierDTO: SupplierDTO): Response
    fun updateSupplier(id: Long, supplierDTO: SupplierDTO): Response
    fun getAllSuppliers(): Response
    fun getSupplierById(id: Long): Response
    fun deleteSupplier(id: Long): Response
}