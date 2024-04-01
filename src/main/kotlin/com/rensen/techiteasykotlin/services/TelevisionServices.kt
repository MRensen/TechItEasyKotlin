package com.rensen.techiteasykotlin.services

import com.rensen.techiteasykotlin.Television
import com.rensen.techiteasykotlin.TelevisionMappers
import com.rensen.techiteasykotlin.dtos.TelevisionInputDto
import com.rensen.techiteasykotlin.dtos.TelevisionOutputDto
import com.rensen.techiteasykotlin.repositories.TelevisionRepository
import org.springframework.stereotype.Service

@Service
class TelevisionService(val televisionRepository: TelevisionRepository, val mappers: TelevisionMappers){
    fun getAllTelevisions(): List<Television> = televisionRepository.findAll()
    fun getTelevisionById(id:Long): Television = televisionRepository.findById(id).orElseThrow()
    fun postTelevision(tvdto: TelevisionInputDto): TelevisionOutputDto{

    }
}
