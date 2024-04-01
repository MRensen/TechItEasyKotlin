package com.rensen.techiteasykotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/*
Deze klasse is leeg. De functie staat niet in de klasse

 */
@SpringBootApplication
class TechItEasyKotlinApplication
//{} Je kunt deze accolades toevoegen, omdat de klasse leeg is. Bij een lege klasse zijn de accolades optioneel.
// Onderstaande functie staat niet in de klasse.

/*
Deze functie is een "top level function" en is niet geassocieerd met de klasse.
 */
fun main(args: Array<String>) {
    /*
    "runApplication" is een inline function.
    Een inline functie roept een andere methode aan. In dit geval is dat:
    `SpringApplication.run(TechItEasyKotlinApplication::class.java , *args)`
     */
    runApplication<TechItEasyKotlinApplication>(*args)

}
