package com.example

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.features.*
import io.ktor.mustache.*

fun main() {
    embeddedServer(Netty, port = 4587, host = "0.0.0.0") {
        configureRouting()
        StaticContent()
        install(Mustache) {
            mustacheFactory = DefaultMustacheFactory("templates")
        }
    }.start(wait = true)
}

