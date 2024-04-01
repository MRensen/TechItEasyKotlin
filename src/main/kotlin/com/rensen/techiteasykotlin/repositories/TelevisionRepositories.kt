package com.rensen.techiteasykotlin.repositories

import com.rensen.techiteasykotlin.Television
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface TelevisionRepository : JpaRepository<Television, Long>{
    fun findByBrandIgnoreCase(brand: String) : Optional<Television>
}