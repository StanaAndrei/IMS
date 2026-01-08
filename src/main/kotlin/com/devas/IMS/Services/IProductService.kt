package com.devas.IMS.Services

import com.devas.IMS.DTOs.ProductDTO
import com.devas.IMS.DTOs.Response
import org.springframework.web.multipart.MultipartFile

interface IProductService {
    fun saveProduct(productDTO: ProductDTO, imageFile: MultipartFile?): Response
    fun updateProduct(productDTO: ProductDTO, imageFile: MultipartFile?): Response
    fun getAllProducts(): Response
    fun getProductById(id: Long): Response
    fun deleteProduct(id: Long): Response
}