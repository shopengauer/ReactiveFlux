package com.server.webflux

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VerticleConfiguration {


    @Bean
    fun init() = CommandLineRunner {


    }

    @Bean
    fun wordService() = WordService()


}