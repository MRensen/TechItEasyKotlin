package com.rensen.techiteasykotlin.services

import com.rensen.techiteasykotlin.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService (userRepository: UserRepository) : UserDetailsService{
    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }
}