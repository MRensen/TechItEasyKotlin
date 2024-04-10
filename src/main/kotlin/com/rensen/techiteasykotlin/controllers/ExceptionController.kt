package com.rensen.techiteasykotlin.controllers

import com.rensen.techiteasykotlin.RecordNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {

    @ExceptionHandler(RecordNotFoundException::class)
    fun handleException(ex: RecordNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(ex.message)
    }
}