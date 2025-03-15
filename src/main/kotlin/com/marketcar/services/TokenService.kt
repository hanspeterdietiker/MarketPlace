package com.marketcar.services

import com.marketcar.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import io.jsonwebtoken.security.Keys
import org.springdoc.core.service.GenericParameterService
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Date

@Service
class TokenService(
    jwtProperties: JwtProperties,
) {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additonalClaims: Map<String, Any> = emptyMap(),
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additonalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser
            .parseSignedClaims(token)
            .payload
    }

    fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return userDetails.username == email && !isExpired(token)
    }
}
