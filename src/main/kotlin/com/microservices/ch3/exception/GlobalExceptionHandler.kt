package com.microservices.ch3.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException::class)
    fun exceptionHandler(servletRequest: HttpServletRequest, exception: Exception): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(HttpStatus.BAD_REQUEST.name, exception.message ?: "bad request").toString())

    data class ExceptionResponse(val exception: String, val message: String)
}