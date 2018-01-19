package com.server.webflux.web

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


@Service
class UploadHandler{


    fun upload(request: ServerRequest) : Mono<ServerResponse> {
        val multiValueMap = request.body(BodyExtractors.toMultipartData())
       // multiValueMap.
        return ServerResponse.ok().build()

    }



}