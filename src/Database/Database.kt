package com.example.Database

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import javax.xml.crypto.Data


class Database(
    in_sUrl: String
    , in_sUser: String
    , in_sPassword: String
) {
    private var url = in_sUrl
    private var user = in_sUser
    private var password = in_sPassword

    private var connection: Connection? = null

    fun Connection(): Boolean {
        try {
            connection = DriverManager.getConnection(url, user, password)
            println("Connect success!")
            return true
        } catch (ex: SQLException) {
            println("Error: " + ex.message)
            return false
        }
    }
    class Builder{
        private var url = "jdbc:postgresql://localhost:5432/youtubetor"
        private var user = "admin"
        private var password= "12345"

        fun Url(in_sUrl: String): Builder {
            url = in_sUrl
            return this
        }

        fun User(in_sUser: String): Builder {
            user = in_sUser
            return this
        }

        fun Password(in_sPassword: String): Builder {
            password = in_sPassword
            return this
        }

        fun Connection() : Boolean {
            return Database(url, user, password).Connection()
        }
    }
}