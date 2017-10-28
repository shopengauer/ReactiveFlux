package com.server.webflux.web

import com.server.webflux.mongo.User
import com.server.webflux.mongo.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class UserHandler {


    @Autowired lateinit var userService: UserService


    fun getAllUsers() : Mono<ServerResponse>  {
       val users = userService.findAllReactive()
       return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(users, User::class.java)
    }


}