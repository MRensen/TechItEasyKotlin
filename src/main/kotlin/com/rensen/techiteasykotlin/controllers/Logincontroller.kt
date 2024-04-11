package com.rensen.techiteasykotlin.controllers

import LoginRequestDto
import com.rensen.techiteasykotlin.User
import com.rensen.techiteasykotlin.config.JwtService
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Logincontroller (val jwtService: JwtService,
                       val authenticationManager: AuthenticationManager){


    @PostMapping("/login")
    fun login(@Valid @RequestBody login: LoginRequestDto) : ResponseEntity<String> {
        val up = UsernamePasswordAuthenticationToken(login.username, login.password)

        val jwt = jwtService.generateToken( authenticationManager.authenticate(up).principal as User )
//        val jwt = ""
        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
            .body("Token generated")

    }
}