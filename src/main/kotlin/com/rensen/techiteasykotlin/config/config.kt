//package com.rensen.techiteasykotlin.config
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import com.fasterxml.jackson.module.kotlin.KotlinFeature
//import com.fasterxml.jackson.module.kotlin.KotlinModule
//import com.fasterxml.jackson.module.kotlin.SingletonSupport
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//
//@Configuration
//class JacksonConfiguration {
//    @Bean
//    fun objectMapper(): ObjectMapper {
//        val objectMapper = ObjectMapper()
//        objectMapper.registerModule(KotlinModule.Builder()
//                .withReflectionCacheSize(512)
//                .configure(KotlinFeature.NullToEmptyCollection, false)
//                .configure(KotlinFeature.NullToEmptyMap, false)
//                .configure(KotlinFeature.NullIsSameAsDefault, true)
//                .configure(KotlinFeature.SingletonSupport, false)
//                .configure(KotlinFeature.StrictNullChecks, false)
//                .build())
//        return objectMapper
//    }
//}
//
