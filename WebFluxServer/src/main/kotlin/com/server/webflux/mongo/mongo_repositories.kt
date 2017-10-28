package com.server.webflux.mongo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findOne
import org.springframework.stereotype.Service

@Service
class UserService{

    @Autowired lateinit var mongoTemplate: MongoTemplate
    @Autowired lateinit var reactiveMongoTemplate: ReactiveMongoTemplate



    fun findAll() = mongoTemplate.findAll(User::class.java,"users")
    fun findAllReactive() = reactiveMongoTemplate.findAll(User::class.java,"users")
   // fun findByName() = reactiveMongoTemplate.findOne<>()
}