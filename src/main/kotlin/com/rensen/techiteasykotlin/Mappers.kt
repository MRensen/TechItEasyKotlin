package com.rensen.techiteasykotlin

import com.rensen.techiteasykotlin.dtos.TelevisionInputDto
import com.rensen.techiteasykotlin.dtos.TelevisionOutputDto
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class TelevisionMappers {
    val mapper = ModelMapper()
    fun mapTelevisionInputDtoToTelevision(dto: TelevisionInputDto): Television {
        var tv: Television = Television()
        mapper.map(dto, tv)
        return tv
    }

    fun mapTelevisionToTelevisionOutputDto(tv: Television): TelevisionOutputDto{
        val dto = mapper.map()
    }
}