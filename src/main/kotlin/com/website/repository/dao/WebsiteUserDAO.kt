package com.website.repository.dao

import com.website.model.WebsiteUser
import com.website.repository.table.WebsiteUserTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class WebsiteUserDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<WebsiteUserDAO>(WebsiteUserTable)

    var playerName by WebsiteUserTable.playerName
    var passWord by WebsiteUserTable.passWord

}

fun WebsiteUserDAO.toModel() = WebsiteUser(playerName, passWord)