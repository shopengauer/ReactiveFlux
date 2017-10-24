package com.server.webflux.mongo

data class User(val id: String, val name: String, val email: String) {
    var disabled: Boolean = false
}