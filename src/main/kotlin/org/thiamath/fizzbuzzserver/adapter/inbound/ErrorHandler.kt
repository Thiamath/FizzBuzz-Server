package org.thiamath.fizzbuzzserver.adapter.inbound

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.thiamath.fizzbuzzserver.domain.service.BadArgumentException

@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BadArgumentException::class)
    fun handleBadRequestError(exception: Exception): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                mapOf(
                    "error" to "Bad Request",
                    "message" to exception.message,
                )
            )
    }

}
