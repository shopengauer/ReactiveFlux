package com.server.webflux

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class DefaultController {

    @Autowired lateinit var wordService: WordService

    @GetMapping(path = arrayOf("/{path}"))
    fun index(@PathVariable path: String): String {
        val rev = path.reversed()
        return "Hello WebFlux $rev"
    }

    @GetMapping(path = arrayOf("/word"))
    fun word(): List<Word> {
        return wordService.getWords()
    }



}

class Word(val token: String, val lang: Language, val translates: List<String>)

enum class Language { ENGLISH, RUSSIAN }