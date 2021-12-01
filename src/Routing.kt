package com.example

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.http.content.file
import io.ktor.http.content.static
import io.ktor.mustache.MustacheContent
import io.ktor.response.*
import java.io.File

data class User(val id: Int, val name: String)

fun Application.configureRouting() {
    routing {
        route("/index")
        {
            get{
                val sampleUser = User(1, "John")
                call.respond(MustacheContent("index.hbs", mapOf("user" to sampleUser)))
            }
        }
        get("/") {
            call.respondText("Hello World kotlin!")
        }
        get("/file") {
            try{
                val html = File("./static/index.html").readText()
                call.respondText(html, ContentType.Text.Html)
            }
            catch(e: Exception){
                val err = e.message.toString()
                call.respondText(err)
            }
        }

    }
}

fun Application.StaticContent() {
    routing {
        static("static") {
            file("index.html")
        }
    }
}
