package com.example

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 21764, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}

