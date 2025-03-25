package com.website.repository.table

import org.jetbrains.exposed.dao.id.IntIdTable

object WebsiteUserTable : IntIdTable("website_user") {

    val playerName = varchar("playername", 32)
    val passWord = varchar("password", 32)
}