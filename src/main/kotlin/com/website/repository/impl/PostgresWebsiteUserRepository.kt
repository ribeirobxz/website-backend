package com.website.repository.impl

import com.website.mapping.suspendTransaction
import com.website.model.WebsiteUser
import com.website.repository.WebsiteUserRepository
import com.website.repository.dao.WebsiteUserDAO
import com.website.repository.dao.toModel
import com.website.repository.table.WebsiteUserTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update

object PostgresWebsiteUserRepository : WebsiteUserRepository {
    override suspend fun findByName(playerName: String): WebsiteUser? = suspendTransaction {
        WebsiteUserDAO.all()
            .firstOrNull { it.playerName == playerName }
            ?.toModel()
    }

    override suspend fun findAll(): List<WebsiteUser?> = suspendTransaction {
        WebsiteUserDAO.all().map { it.toModel() }
    }

    override suspend fun save(websiteUser: WebsiteUser): Unit = suspendTransaction {
        WebsiteUserDAO.new {
            playerName = websiteUser.playerName
            passWord = websiteUser.passWord
        }
    }

    override suspend fun update(websiteUser: WebsiteUser): Unit = suspendTransaction {
        WebsiteUserTable.update({
            WebsiteUserTable.playerName eq websiteUser.playerName
        }) {
            it[passWord] = websiteUser.passWord
        }
    }

    override suspend fun delete(websiteUser: WebsiteUser): Unit = suspendTransaction {
        WebsiteUserTable.deleteWhere {
            playerName eq websiteUser.playerName
        }
    }

    override suspend fun deleteAll(): Unit = suspendTransaction {
        WebsiteUserTable.deleteAll()
    }
}