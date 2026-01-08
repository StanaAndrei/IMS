package com.devas.IMS.Services

import com.devas.IMS.DTOs.LoginRequest
import com.devas.IMS.DTOs.RegisterRequest
import com.devas.IMS.DTOs.Response
import com.devas.IMS.DTOs.UserDTO
import com.devas.IMS.Entities.User

interface IUserService {
    fun registerUser(registerRequest: RegisterRequest): Response
    fun loginUser(loginRequest: LoginRequest): Response
    fun getAllUsers(): Response
    fun getCurrentLoggedInUser(): User
    fun updateUser(id: Long, userDTO: UserDTO): Response
    fun deleteUser(id: Long): Response
    fun getUserTransactions(id: Long): Response
}