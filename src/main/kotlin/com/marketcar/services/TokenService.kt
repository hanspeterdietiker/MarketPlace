package com.marketcar.services

import com.marketcar.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

@Service
class TokenService(
    jwtProperties: JwtProperties,
) {
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray(),
    )

    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap(),
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()


    private fun getAllClaims(token: String): Claims {
        val cleanToken = token.trim()
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser
            .parseSignedClaims(cleanToken)
            .payload
    }

    fun extractName(token: String): String? =
        getAllClaims(token)
            .subject

    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    fun isValid(token: String, userDetails: UserDetails): Boolean {
        val name = extractName(token)
        return userDetails.username == name && !isExpired(token)
    }
}
