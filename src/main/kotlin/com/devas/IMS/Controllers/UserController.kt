package com.devas.IMS.Controllers

import com.devas.IMS.DTOs.Response
import com.devas.IMS.DTOs.UserDTO
import com.devas.IMS.Entities.User
import com.devas.IMS.Services.IUserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: IUserService
) {

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getAllUsers(): ResponseEntity<Response> {
        return ResponseEntity.ok(userService.getAllUsers())
    }

    @PutMapping("/update/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userDTO: UserDTO
    ): ResponseEntity<Response> {
        return ResponseEntity.ok(userService.updateUser(id, userDTO))
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Response> {
        return ResponseEntity.ok(userService.deleteUser(id))
    }

    @GetMapping("/transactions/{userId}")
    fun getUserAndTransactions(@PathVariable userId: Long): ResponseEntity<Response> {
        return ResponseEntity.ok(userService.getUserTransactions(userId))
    }

    @GetMapping("/current")
    fun getCurrentUser(): ResponseEntity<User> {
        return ResponseEntity.ok(userService.getCurrentLoggedInUser())
    }
}