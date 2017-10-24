package com.server.webflux.mongo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class UserService{

    @Autowired lateinit var mongoTemplate: MongoTemplate


    fun findAll() = mongoTemplate.findAll(User::class.java,"users")

}