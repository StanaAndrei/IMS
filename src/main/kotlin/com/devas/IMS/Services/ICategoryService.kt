package com.devas.IMS.Services

import com.devas.IMS.DTOs.CategoryDTO
import com.devas.IMS.DTOs.Response

interface ICategoryService {
    fun createCategory(categoryDTO: CategoryDTO): Response
    fun getAllCategories(): Response
    fun getCategoryById(id: Long): Response
    fun updateCategory(id: Long, categoryDTO: CategoryDTO): Response
    fun deleteCategory(id: Long): Response
}