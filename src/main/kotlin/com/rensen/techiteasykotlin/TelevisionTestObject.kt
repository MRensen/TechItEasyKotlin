package com.rensen.techiteasykotlin

import java.time.LocalDateTime

/*
Data class in Kotlin is zoiets als een Record in java.
Een Data Class heeft velden nodig.

"val" velden zijn final.
"var" velden zijn changeable.
val Velden in Kotlin zijn niet-nullabel.

Velden met een ? achter het type zijn wel nullable.

Je kunt default waarden aan velden mee geven door er een `= [waarde]` achter te zetten.

In Kotlin kun je extensions functions schrijven.
Dit zijn extra functies voor een klasse of datatype, bovenop de bestaande functies van die klasse.


 */
data class TelevisionTestObject (
        val id: Long,
        var name: String,
        var brand: String,
        var model: String?,
        var createdAt: LocalDateTime = LocalDateTime.now(),
        val price: Int,
        var amount: Int,
        var totalPrice: Int = price.total(amount) //price is een int. Int heeft geen "total()" methode. Deze staat in extensions.kt
)