package com.rensen.techiteasykotlin.dtos

data class TelevisionInputDto (
        var type: String? = null,
        var brand: String? = null,
        var name: String? = null,
        var screenType: String? = null,
        var screenQuality: String? = null,
        var price: Double? = null,
        var availableSize: Double? = null,
        var refreshRate: Double? = null,
        var smartTv: Boolean? = null,
        var wifi: Boolean? = null,
        var voiceControl: Boolean? = null,
        var hdr: Boolean? = null,
        var blueTooth: Boolean? = null,
        var ambiLight: Boolean? = null,
        var originalStock: Int? = null,
        var sold: Int? = null
)

data class TelevisionOutputDto (
        var id: Long?,
        var type: String?,
        var brand: String?,
        var name: String? ,
        var screenType: String? ,
        var screenQuality: String? ,
        var price: Double? ,
        var availableSize: Double? ,
        var refreshRate: Double? ,
        var smartTv: Boolean? ,
        var wifi: Boolean? ,
        var voiceControl: Boolean? ,
        var hdr: Boolean? ,
        var blueTooth: Boolean? ,
        var ambiLight: Boolean? ,
        var originalStock: Int? ,
        var sold: Int? 
)



