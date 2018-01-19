package com.server.webflux.web


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
class UserWebConfig {

    @Autowired lateinit var userHandler: UserHandler


    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> {
        return RouterFunctions
                .route(RequestPredicates.GET("/reactive"), HandlerFunction { userHandler.getAllUsers() })
              //  .andRoute(RequestPredicates.GET("/reactive/{name}"), HandlerFunction { userHandler. }))

    }

//    @Bean
//    fun routerUpload(): RouterFunction<ServerResponse> {
//        return RouterFunctions
//                .route(RequestPredicates.POST("/upload")
//                        .and(RequestPredicates.contentType(MediaType.MULTIPART_FORM_DATA)), HandlerFunction { uploadHandler.upload() })
//
//    }

    @Autowired lateinit var uploadHandler: UploadHandler

}