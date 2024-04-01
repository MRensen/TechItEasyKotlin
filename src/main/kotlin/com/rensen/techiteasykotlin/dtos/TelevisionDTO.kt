package com.rensen.techiteasykotlin.dtos

data class TelevisionInputDto (
        val type: String? = null,
        val brand: String? = null,
        val name: String? = null,
        val screenType: String? = null,
        val screenQuality: String? = null,
        val price: Double? = null,
        val availableSize: Double? = null,
        val refreshRate: Double? = null,
        val smartTv: Boolean? = null,
        val wifi: Boolean? = null,
        val voiceControl: Boolean? = null,
        val hdr: Boolean? = null,
        val blueTooth: Boolean? = null,
        val ambiLight: Boolean? = null,
        val originalStock: Int? = null,
        val sold: Int? = null
)

data class TelevisionOutputDto (
        val id: Long,
        val type: String?,
        val brand: String?,
        val name: String? ,
        val screenType: String? ,
        val screenQuality: String? ,
        val price: Double? ,
        val availableSize: Double? ,
        val refreshRate: Double? ,
        val smartTv: Boolean? ,
        val wifi: Boolean? ,
        val voiceControl: Boolean? ,
        val hdr: Boolean? ,
        val blueTooth: Boolean? ,
        val ambiLight: Boolean? ,
        val originalStock: Int? ,
        val sold: Int? 
)

