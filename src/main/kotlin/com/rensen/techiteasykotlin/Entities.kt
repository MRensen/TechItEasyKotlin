package com.rensen.techiteasykotlin

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


// Voor Entities gebruik je geen "data class" (dat is als een Record)
// Voor Entities heb je een default constructor nodig.
@Entity
class Television {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var type: String? = null
    var brand: String? = null
    var name: String? = null
    var screenType: String? = null
    var screenQuality: String? = null
    var price: Double? = null
    var availableSize: Double? = null
    var refreshRate: Double? = null
    var smartTv: Boolean? = null
    var wifi: Boolean? = null
    var voiceControl: Boolean? = null
    var hdr: Boolean? = null
    var blueTooth: Boolean? = null
    var ambiLight: Boolean? = null
    var originalStock: Int? = null
    var sold: Int? = null

}