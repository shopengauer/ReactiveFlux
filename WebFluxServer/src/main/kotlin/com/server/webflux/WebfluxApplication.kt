package com.server.webflux

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class WebfluxApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebfluxApplication::class.java, *args)
}
