package com.server.webflux.web


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Configuration
class UserWebConfig : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request
        return if (request.uri.path == "/") {
            chain.filter(exchange.mutate().request(request.mutate().path("/index.html").build()).build())
        } else {
            chain.filter(exchange)
        }
    }


    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> {
        return RouterFunctions.resources("/**", ClassPathResource("static/"))
                .andRoute(RequestPredicates.POST("/upload"), HandlerFunction { request ->
                    request.body(BodyExtractors.toMultipartData()).flatMap { parts ->
                        val part = parts.toSingleValueMap()
                        val filePart = part["files"] as FilePart
                        Files.createFile(Paths.get(filePart.filename()))
                        filePart.transferTo(File(filePart.filename()))
                        ServerResponse.ok().body(BodyInserters.fromObject("OK"))
                    }
                })

    }


}