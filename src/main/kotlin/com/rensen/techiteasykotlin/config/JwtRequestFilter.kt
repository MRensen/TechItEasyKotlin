package com.rensen.techiteasykotlin.config

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtRequestFilter(private val jwtService: JwtService, private val userDetailsService: UserDetailsService) :
    OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
         request: HttpServletRequest,
         response: HttpServletResponse,
         filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")
        var username: String? = null
        var roles: List<GrantedAuthority?>? = ArrayList()
        var jwt: String? = null
        if (authorizationHeader != null &&
            authorizationHeader.startsWith("Bearer ")
        ) {
            jwt = authorizationHeader.substring(7)
            username = jwtService.extractUsername(jwt)
            roles = jwtService.extractRoles(jwt)
        }
        if (username != null &&
            SecurityContextHolder.getContext().authentication == null
        ) {
            if (jwtService.validateToken(jwt!!)!!) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                    username, null,
                    roles
                )
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}
