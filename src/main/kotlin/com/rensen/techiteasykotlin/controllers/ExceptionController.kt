package com.rensen.techiteasykotlin.controllers

import com.rensen.techiteasykotlin.RecordNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionController {

    @ExceptionHandler(RecordNotFoundException::class)
    fun handleException(ex: RecordNotFoundException): ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(ex.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleException(ex: MethodArgumentNotValidException) = ex.bindingResult.fieldErrors.map { it.defaultMessage }.toList()
}