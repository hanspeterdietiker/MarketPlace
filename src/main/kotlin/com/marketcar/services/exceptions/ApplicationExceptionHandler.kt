package com.marketcar.services.exceptions


import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ApplicationExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(EntityNotFoundException::class)
    fun entityNotFoundException(e: EntityNotFoundException): ResponseEntity<String> {
        return ResponseEntity(
            e.message ?: "ERROR: Entity not found",
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(Exception::class)
    fun genericException(e: Exception): ResponseEntity<String> {
        return ResponseEntity(
            e.message ?: "ERROR: Internal server error, please try again later",
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(PersistenceException::class)
    fun persistenceException(e: PersistenceException): ResponseEntity<String> {
        return ResponseEntity(e.message ?: "ERROR: Error during database operation",
            HttpStatus.BAD_REQUEST)
    }

}
