package com.rensen.techiteasykotlin.services

import com.rensen.techiteasykotlin.RecordNotFoundException
import com.rensen.techiteasykotlin.dtos.TelevisionInputDto
import com.rensen.techiteasykotlin.dtos.TelevisionOutputDto
import com.rensen.techiteasykotlin.mappers.mapTelevisionInputDtoToTelevision
import com.rensen.techiteasykotlin.mappers.mapTelevisionToTelevisionOutputDto
import com.rensen.techiteasykotlin.repositories.TelevisionRepository
import org.springframework.stereotype.Service

@Service
class TelevisionService(val televisionRepository: TelevisionRepository){
    fun getAllTelevisions(): List<TelevisionOutputDto> =
        televisionRepository.findAll()
            .map { it.mapTelevisionToTelevisionOutputDto() }
    fun getTelevisionById(id:Long): TelevisionOutputDto =
        televisionRepository.findById(id).orElseThrow({RecordNotFoundException("Geen televisie met id: [$id] gevonden")})
            .mapTelevisionToTelevisionOutputDto()
    fun postTelevision(tvdto: TelevisionInputDto): TelevisionOutputDto =
        televisionRepository.save(tvdto.mapTelevisionInputDtoToTelevision())
            .mapTelevisionToTelevisionOutputDto()
    fun putTelevision(id: Long, tvDto: TelevisionInputDto) : TelevisionOutputDto {
        val tv = tvDto.mapTelevisionInputDtoToTelevision()
        tv.id = id
        return televisionRepository.save(tv).mapTelevisionToTelevisionOutputDto()
    }
    fun deleteTelevision(id: Long) =
        if (televisionRepository.existsById(id)) televisionRepository.deleteById(id)
        else throw RecordNotFoundException("Television with id $id not found")


}
