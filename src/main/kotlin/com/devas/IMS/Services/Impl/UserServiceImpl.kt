package com.devas.IMS.Services.Impl

import com.devas.IMS.DTOs.*
import com.devas.IMS.Entities.User
import com.devas.IMS.Enums.UserRole
import com.devas.IMS.Exceptions.InvalidCredentialsException
import com.devas.IMS.Exceptions.NotFoundException
import com.devas.IMS.Repos.UserRepository
import com.devas.IMS.Security.JwtUtils
import com.devas.IMS.Services.IUserService
import org.modelmapper.ModelMapper
import org.modelmapper.TypeToken
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val modelMapper: ModelMapper,
    private val jwtUtils: JwtUtils
) : IUserService {

    private val log = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun registerUser(registerRequest: RegisterRequest): Response {
        // Default to MANAGER if role is null
        val role = registerRequest.role ?: UserRole.MANAGER

        val userToSave = User().apply {
            name = registerRequest.name
            email = registerRequest.email
            password = passwordEncoder.encode(registerRequest.password)
            phoneNumber = registerRequest.phoneNumber
            this.role = role
        }

        userRepository.save(userToSave)

        return Response(
            status = 200,
            message = "User created successfully"
        )
    }

    override fun loginUser(loginRequest: LoginRequest): Response {
        // findByEmail returns User?, so we handle the null case
        val user = userRepository.findByEmail(loginRequest.email!!)
            ?: throw NotFoundException("Email not Found")

        if (!passwordEncoder.matches(loginRequest.password, user.password)) {
            throw InvalidCredentialsException("Password does not match")
        }

        val token = jwtUtils.generateToken(user.email!!)

        return Response(
            status = 200,
            message = "User logged in successfully",
            role = user.role,
            token = token,
            expirationTime = "6 months"
        )
    }

    override fun getAllUsers(): Response {
        val users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))

        // Create a type token for List<UserDTO>
        val listType = object : TypeToken<List<UserDTO>>() {}.type
        val userDTOs: List<UserDTO> = modelMapper.map(users, listType)

        // Clear transactions to avoid heavy response payload
        userDTOs.forEach { it.transactions = null }

        return Response(
            status = 200,
            message = "success",
            users = userDTOs
        )
    }

    override fun getCurrentLoggedInUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
        val email = authentication?.name

        val user = userRepository.findByEmail(email)
            ?: throw NotFoundException("User Not Found")

        // Clear transactions to avoid serialization issues/infinite recursion
        user.transactions = mutableListOf()

        return user
    }

    override fun updateUser(id: Long, userDTO: UserDTO): Response {
        val existingUser = userRepository.findById(id)
            .orElseThrow { NotFoundException("User Not Found") }

        // Update fields only if they are not null
        userDTO.email?.let { existingUser.email = it }
        userDTO.name?.let { existingUser.name = it }
        userDTO.phoneNumber?.let { existingUser.phoneNumber = it }
        userDTO.role?.let { existingUser.role = it }

        if (!userDTO.password.isNullOrEmpty()) {
            // FIX: The original Java code set this to 'phoneNumber'. I corrected it to 'password'.
            existingUser.password = passwordEncoder.encode(userDTO.password)
        }

        userRepository.save(existingUser)

        return Response(
            status = 200,
            message = "User Successfully updated"
        )
    }

    override fun deleteUser(id: Long): Response {
        if (!userRepository.existsById(id)) {
            throw NotFoundException("User Not Found")
        }
        userRepository.deleteById(id)

        return Response(
            status = 200,
            message = "User Successfully Deleted"
        )
    }

    override fun getUserTransactions(id: Long): Response {
        val user = userRepository.findById(id)
            .orElseThrow { NotFoundException("User Not Found") }

        val userDTO = modelMapper.map(user, UserDTO::class.java)

        // Nullify circular references in the DTO
        userDTO.transactions?.forEach { transactionDTO ->
            transactionDTO.user = null
            transactionDTO.supplier = null
        }

        return Response(
            status = 200,
            message = "success",
            user = userDTO
        )
    }
}