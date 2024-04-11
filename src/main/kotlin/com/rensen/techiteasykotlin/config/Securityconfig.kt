package com.rensen.techiteasykotlin.config

import com.rensen.techiteasykotlin.services.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/*
Deze klasse kan niet in een package.
De Configuratie klasse moet blijkbaar op hetzelfde niveau als de Applicatie klasse, want anders worden de context-beans (zoals jwtService) niet herkent
 */
@Configuration
@EnableWebSecurity
class Securityconfig(
    val userService: UserService,
    val jwtService: JwtService
) {

    @Bean
    public open fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    public open fun customAuthenticationManager(http: HttpSecurity): AuthenticationManager {
        val builder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        builder.userDetailsService(userService).passwordEncoder(passwordEncoder())
        return builder.build()
    }

    @Bean
    fun filterChain( http: HttpSecurity): SecurityFilterChain{
        http
            .httpBasic{it.disable()}
//            .authenticationManager(customAuthenticationManager())
            .authorizeHttpRequests { it
                .requestMatchers("/**").permitAll()
            }
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .addFilterBefore(JwtRequestFilter(jwtService, userService), UsernamePasswordAuthenticationFilter::class.java)
            .cors(Customizer.withDefaults())
            .csrf{it.disable()}
        return http.build()
    }

}