package com.website.repository.dao

import com.website.cache.permission.PermissionCache
import com.website.model.user.group.Group
import com.website.model.user.group.GroupData
import com.website.repository.table.GroupTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GroupDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GroupDAO>(GroupTable)

    var groupId by GroupTable.groupId
    var priority by GroupTable.priority
    var name by GroupTable.name
    var description by GroupTable.description
    var color by GroupTable.color
    var permissions by GroupTable.permissions

}

fun GroupDAO.toModel() = Group(
    groupId,
    priority,
    GroupData(name, description, color),
    permissions.mapNotNull { PermissionCache.getPermission(it) }.toMutableList()
)
