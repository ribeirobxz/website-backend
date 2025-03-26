package com.website.model.user.group

import com.website.model.user.permission.Permission
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: String,
    val priority: Int,
    val groupData: GroupData,
    val permissions: MutableList<Permission>
) {

    fun addPermission(permission: Permission) = permissions.add(permission)
    fun removePermission(permission: Permission) = permissions.remove(permission)

    fun hasPermission(permission: Permission): Boolean = permissions.contains(permission)

}

@Serializable
data class GroupData(
    val name: String,
    val description: String,
    val color: String
)
