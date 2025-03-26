package com.website.repository.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.VarCharColumnType

object GroupTable : IntIdTable("groups") {

    val groupId = varchar("id", 16)
    val priority = integer("priority")
    val name = varchar("name", 32)
    val description = text("description")
    val color = varchar("color", 8)
    val permissions = array("permissions", VarCharColumnType(255))
}