package com.example

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.mustache.MustacheContent
import io.ktor.response.*
import java.io.File

import com.example.Models.User
import com.example.Database.Database
import io.github.cdimascio.dotenv.dotenv

// ENV VAR
val dotenv = dotenv()

fun Application.СonfigureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World kotlin!")
        }
        get("/file") {
            try{
                val html = File("./static/index.html").readText()
                call.respondText(html, ContentType.Text.Html)
            }
            catch(exception: Exception){
                val err = exception.message.toString()
                call.respondText(err)
            }
        }
    }
}

fun Application.Index() {
    routing {
        route("/index") {
            get{
                // Переменные окружения для подк. бд
                val l_dbUrl = dotenv["DB_URL"]
                val l_dbUser = dotenv["DB_USER"]
                val l_dbPassword = dotenv["DB_PASSWORD"]

                // Подключение к бд
                val statusConnectDB = Database.Builder()
                    .Url(l_dbUrl)
                    .User(l_dbUser)
                    .Password(l_dbPassword)
                    .Connection()

                if(statusConnectDB)
                {
                    val sampleUser = User(1, "John", 20, "Script", "Govno")
                    call.respond(MustacheContent(
                        "index.hbs",
                        mapOf("user" to sampleUser))
                    )
                }
            }
        }
    }
}
