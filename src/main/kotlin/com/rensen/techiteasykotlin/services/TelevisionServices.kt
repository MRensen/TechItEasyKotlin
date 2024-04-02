package com.rensen.techiteasykotlin.services

import com.rensen.techiteasykotlin.RecordNotFoundException
import com.rensen.techiteasykotlin.Television
import com.rensen.techiteasykotlin.TelevisionMappers
import com.rensen.techiteasykotlin.dtos.TelevisionInputDto
import com.rensen.techiteasykotlin.dtos.TelevisionOutputDto
import com.rensen.techiteasykotlin.repositories.TelevisionRepository
import org.springframework.stereotype.Service

@Service
class TelevisionService(val televisionRepository: TelevisionRepository, val mappers: TelevisionMappers){
    fun getAllTelevisions(): List<TelevisionOutputDto> = mappers.mapTelevisionListToTelevisionOutputDtoList(televisionRepository.findAll())
    fun getTelevisionById(id:Long): TelevisionOutputDto = mappers.mapTelevisionToTelevisionOutputDto(televisionRepository.findById(id).orElseThrow())
    fun postTelevision(tvdto: TelevisionInputDto): TelevisionOutputDto = mappers.mapTelevisionToTelevisionOutputDto(televisionRepository.save(mappers.mapTelevisionInputDtoToTelevision(tvdto)))
    fun putTelevision(id: Long, tvDto: TelevisionInputDto) : TelevisionOutputDto {
        val tv = mappers.mapTelevisionInputDtoToTelevision(tvDto)
        tv.id = id
        return mappers.mapTelevisionToTelevisionOutputDto(televisionRepository.save(tv))
    }
    fun deleteTelevision(id: Long) = if (televisionRepository.existsById(id)) televisionRepository.deleteById(id) else throw RecordNotFoundException("Television with id $id not found")


}
