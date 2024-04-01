package com.rensen.techiteasykotlin

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.NoSuchElementException

@ControllerAdvice
class ExceptionHandlers{

    @ExceptionHandler()
            fun handle(e: NoSuchElementException) {
                ResponseEntity("Record not found", HttpStatus.NOT_FOUND)
            }


}
