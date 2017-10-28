package com.server.webflux

import com.server.webflux.mongo.User
import com.server.webflux.mongo.UserService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class WebfluxApplicationTests {



	@Test
	fun contextLoads() {

		val users = userService.findAll();
        println(users)
      //  userService.findAllReactive().subscribe({s-> println(s)})
        println(userService.findAllReactive().toStream().forEach({d -> println(d)}))
	}

    @Autowired lateinit var userService: UserService

}
