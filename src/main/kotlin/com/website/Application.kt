package com.website

import com.website.database.configureDatabases
import com.website.security.configureSecurity
import com.website.serialization.configureSerialization
import com.website.routes.configureRouting
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*

val dotenv = dotenv {
    ignoreIfMissing = true
}

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureDatabases()
}

