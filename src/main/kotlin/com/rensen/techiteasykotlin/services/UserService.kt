package com.rensen.techiteasykotlin.services

import com.rensen.techiteasykotlin.RecordNotFoundException
import com.rensen.techiteasykotlin.repositories.UserRepository
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService (val userRepository: UserRepository) : UserDetailsService{
    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findById(username?:"").orElseThrow { RecordNotFoundException("Authentication Failed") }

    }

    fun getById(username: String) = userRepository.findById(username)
}