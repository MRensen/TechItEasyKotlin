package com.rensen.techiteasykotlin.mappers

import com.rensen.techiteasykotlin.Television
import com.rensen.techiteasykotlin.dtos.TelevisionInputDto
import com.rensen.techiteasykotlin.dtos.TelevisionOutputDto
import org.springframework.stereotype.Service

//@Service
//class TelevisionMappers {
    fun TelevisionInputDto.mapTelevisionInputDtoToTelevision(): Television {
        val dto = this
        val tv = Television()
        tv.id = null
        tv.hdr = dto.hdr
        tv.ambiLight = dto.ambiLight
        tv.brand = dto.brand
        tv.name = dto.name  
        tv.availableSize = dto.availableSize
        tv.blueTooth = dto.blueTooth
        tv.originalStock = dto.originalStock
        tv.price = dto.price
        tv.refreshRate = dto.refreshRate
        tv.screenQuality = dto.screenQuality
        tv.smartTv = dto.smartTv
        tv.screenType = dto.screenType
        tv.sold = dto.sold
        tv.voiceControl = dto.voiceControl
        tv.wifi = dto.wifi
        tv.type = dto.type
        return tv
    } 
    fun Television.mapTelevisionToTelevisionOutputDto(): TelevisionOutputDto{
        val tv = this
        return TelevisionOutputDto(
                id = tv.id,
                        hdr = tv.hdr,
                        ambiLight = tv.ambiLight,
                        brand = tv.brand,
                        name = tv.name,
                        availableSize = tv.availableSize,
                        blueTooth = tv.blueTooth,
                        originalStock = tv.originalStock,
                        price = tv.price,
                        refreshRate = tv.refreshRate,
                        screenQuality = tv.screenQuality,
                        smartTv = tv.smartTv,
                        screenType = tv.screenType,
                        sold = tv.sold,
                        voiceControl = tv.voiceControl,
                        wifi = tv.wifi,
                type =  tv.type
        )
    }

//}