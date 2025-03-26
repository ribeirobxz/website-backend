package com.website.repository.impl

import com.website.mapping.suspendTransaction
import com.website.model.user.group.Group
import com.website.repository.GroupRepository
import com.website.repository.dao.GroupDAO
import com.website.repository.dao.toModel
import com.website.repository.table.GroupTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update

object PostgresGroupRepository : GroupRepository {
    override suspend fun findById(groupId: String): Group? = suspendTransaction {
        GroupDAO.all()
            .firstOrNull { it.groupId == groupId }
            ?.toModel()
    }

    override suspend fun findAll(): List<Group?> = suspendTransaction {
        GroupDAO.all().map { it.toModel() }
    }

    override suspend fun save(group: Group): Unit = suspendTransaction {
        GroupDAO.new {
            groupId = group.id
            name = group.groupData.name
            description = group.groupData.description
            color = group.groupData.description
            permissions = group.permissions.map { it.id }
        }
    }

    override suspend fun update(group: Group): Unit = suspendTransaction {
        GroupTable.update({
            GroupTable.groupId eq group.id
        }) {
            it[name] = group.groupData.name
            it[description] = group.groupData.description
            it[color] = group.groupData.color
            it[permissions] = group.permissions.map { permission -> permission.id }
        }
    }

    override suspend fun delete(group: Group): Unit = suspendTransaction {
        GroupTable.deleteWhere {
            groupId eq group.id
        }
    }

    override suspend fun deleteAll(): Unit = suspendTransaction {
        GroupTable.deleteAll()
    }

}