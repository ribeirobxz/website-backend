package com.website.database

import com.website.dotenv
import org.jetbrains.exposed.sql.Database

fun configureDatabases() {
    val database = dotenv["POSTGRES_DATABASE"]
    val user = dotenv["POSTGRES_USER"]
    val password = dotenv["POSTGRE_PASSWORD"]

    Database.connect(
        "jdbc:postgresql://localhost:5432/$database",
        user = user,
        password = password
    )
}