package com.rensen.techiteasykotlin.controllers;

import com.rensen.techiteasykotlin.TelevisionTestObject
import com.rensen.techiteasykotlin.dtos.TelevisionInputDto
import com.rensen.techiteasykotlin.dtos.TelevisionOutputDto
import com.rensen.techiteasykotlin.services.TelevisionService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.time.LocalDateTime


/*
In Kotlin kunnen meerdere klassen in 1 file leven.
Het is dus prima om alle controllers in 1 controller file te zetten. (ipv een controller package)
 */

@RestController //De annotaties zijn hetzelfde in Kotlin en Java
@RequestMapping("/listtest/televisions")
class TelevisionTestListController {

    /*
    Hieronder wordt een lijst gemaakt. Een mutable lijst.
    Daarin worden op twee manieren een object aangemaakt.
    Je kunt dus ook expliciet de naam van de parameter benoemen, dan maakt de volgorde niet uit.
    Let er wel op dat waardes niet null mogen zijn als er geen ? achter staat.
     */
    var televisionsList = mutableListOf<TelevisionTestObject>(TelevisionTestObject(2L,"blabla", "bla", null, LocalDateTime.now(), 90, 80),
            TelevisionTestObject(
                    id = 1L,
                    name = "Philips AmbiLight",
                    brand = "Philips",
                    amount = 300,
                    model = null,
                    price = 100))

    @GetMapping
    /*
    Door `= [return value]` achter de functiedeclaratie te zetten, kun je het heel kort opschrijven.
    Het kan dus op twee manieren. De een heeft een returntype, de ander niet.
     */
//    fun getAllTelevisions(): MutableList<TelevisionDto> {return televisionsList}
    fun getAllTelevisions() = televisionsList;

    @GetMapping("/{id}")
    fun getTelevisionById(@PathVariable id: Long) =
            // De `find` methode verwaht een lambda
            // Een lambda hoeft niet in de parameters, het mag ook er buiten
            //      `televisionsList.find {televisionDto -> televisionDto.id == id }`
            // Je kunt een
            televisionsList.find({tv -> tv.id == id })
            // Gebruikt de ternary operator om een exception te gooien als er niets gevonden wordt.
            // Je hoeft geen `new` keywordt te gebruiken bij het maken van objecten of gooien van exceptions
                    ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @PostMapping
    fun postTelevision(@RequestBody television: TelevisionTestObject){
        televisionsList.add(television)
    }

    @PutMapping("/{id}")
    fun putTelevision(@RequestBody television: TelevisionTestObject, @PathVariable id: Long): TelevisionTestObject {
        // Je kunt `it` gebruiken in plaats van een lambda variabele definieren.
        var found = televisionsList.find { it.id == id } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        // Alle "val" heb ik veranderd naar "var" omdat ze anders immutable zijn.
        found.amount = television.amount
        found.brand = television.brand
        found.model = television.model
        found.createdAt = television.createdAt
        found.name = television.name
        found.totalPrice = television.totalPrice
        return found
    }

    @DeleteMapping("/{id}")
    fun removeTelevision(@PathVariable id: Long){
        televisionsList.removeIf{it.id == id} ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}

@RestController
@RequestMapping("/televisions")
class TelevisionController(
        val televisionService: TelevisionService /*Constructor injection (primary constructor in class header)*/
){
    @GetMapping
    fun getAllTelevisions() = ResponseEntity.ok(televisionService.getAllTelevisions());

    @GetMapping("/{id}")
    fun getTelevisionById(@PathVariable id: Long) = ResponseEntity.ok(televisionService.getTelevisionById(id))

    @PostMapping
    fun postTelevision(@Valid @RequestBody dto: TelevisionInputDto, request: HttpServletRequest) : ResponseEntity<Void>{
        val tv: TelevisionOutputDto = televisionService.postTelevision(dto)
        val baseUrl = ServletUriComponentsBuilder.fromCurrentRequest()
                .build()
                .toUriString()
        // Variabelen kun je makkelijk in een String gebruiken met $, gebruik ${} om code uit te voeren.
        val location: URI = URI.create("$baseUrl/${tv.id}") // Modify this according to your resource

        return ResponseEntity.created(location).build()
    }

    @PutMapping("/{id}")
    fun putTelevision(@PathVariable id: Long, @Valid @RequestBody dto: TelevisionInputDto) = ResponseEntity.ok(televisionService.putTelevision(id,dto))

    @DeleteMapping("/{id}")
    fun deleteTelevision(@PathVariable id: Long) = ResponseEntity(televisionService.deleteTelevision(id), HttpStatus.NO_CONTENT)
}