package com.rensen.techiteasykotlin.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

@Service
class JwtService {
    private val SECRET_KEY = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"


    private val AUDIENCE = "test"

    private val ROLES_CLAIMS_NAME = "roles"

     fun getSigningKey(): Key? {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun extractUsername(token: String): String? {
        return extractClaim(token) { obj: Claims -> obj.subject }
    }

    fun extractAudience(token: String): String {
        return extractClaim(token) { obj: Claims -> obj.audience }
    }

    fun extractRoles(token: String): List<GrantedAuthority>? {
        val claims = extractAllClaims(token)
        val roles = claims.get(
            ROLES_CLAIMS_NAME,
            List::class.java
        ) ?: return emptyList()
        return roles
            .map {
                SimpleGrantedAuthority(
                    it.toString()
                )

            }

    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token) { obj: Claims -> obj.expiration }
    }

    private fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean? {
        return extractExpiration(token).before(Date())
    }

    fun generateToken(userDetails: UserDetails): String? {
        return generateToken(userDetails, 1000 * 60 * 60 * 24 * 10L) //tien dagen in ms
    }

    fun generateToken(userDetails: UserDetails, milliSeconds: Long): String? {
        val claims: MutableMap<String, Any?> = HashMap()
        val roles = userDetails.authorities.stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.toList())
        claims[ROLES_CLAIMS_NAME] = roles
        return createToken(claims, userDetails.username, milliSeconds) //time in milliseconds
    }

    private fun createToken(claims: Map<String, Any?>, subject: String, milliSeconds: Long): String? {
        val currentTime = System.currentTimeMillis()
        return createToken(claims, subject, currentTime, milliSeconds)
    }

    private fun createToken(claims: Map<String, Any?>, subject: String, currentTime: Long, validPeriod: Long): String? {
        return Jwts.builder()
            .setClaims(claims)
            .setAudience(AUDIENCE)
            .setSubject(subject)
            .setIssuedAt(Date(currentTime))
            .setExpiration(Date(currentTime + validPeriod))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean? {
        return try {
            val tokenAudience = extractAudience(token)
            val isAudienceValid = tokenAudience == AUDIENCE
            !isTokenExpired(token)!! && isAudienceValid
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}