package com.website.repository.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.VarCharColumnType

object WebsiteUserTable : IntIdTable("website_user") {

    val uniqueId = uuid("uniqueid")
    val playerName = varchar("playername", 32)
    val passWord = varchar("password", 32)
    val groups =  array("groups", VarCharColumnType())
}