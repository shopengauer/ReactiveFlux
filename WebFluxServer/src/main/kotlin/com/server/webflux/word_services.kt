package com.server.webflux

import org.springframework.stereotype.Service


class WordService{

    fun getWords():List<Word> = listOf(Word("simple", Language.ENGLISH, listOf("простой", "не сложный")),
            Word("карандаш", Language.RUSSIAN, listOf("pencil")))

}