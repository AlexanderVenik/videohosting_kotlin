package com.example

import com.github.mustachejava.DefaultMustacheFactory
import io.github.cdimascio.dotenv.dotenv
import io.ktor.application.install
import io.ktor.mustache.Mustache
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    // ENV VAR
    val dotenv = dotenv()
    val PORT = dotenv["PORT"].toInt()
    val HOST = dotenv["HOST"]

    embeddedServer(Netty, port = PORT, host = HOST) {
        configureRouting()
        StaticContent()
        install(Mustache) {
            mustacheFactory = DefaultMustacheFactory("templates")
        }
    }.start(wait = true)
}


