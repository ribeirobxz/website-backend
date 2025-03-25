package com.website.model.user.group

import com.website.model.user.permission.Permission
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: String,
    val groupData: GroupData,
    val permissions: Array<Permission>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Group

        if (id != other.id) return false
        if (groupData != other.groupData) return false
        if (!permissions.contentEquals(other.permissions)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + groupData.hashCode()
        result = 31 * result + permissions.contentHashCode()
        return result
    }

}

@Serializable
data class GroupData(
    val name: String,
    val description: String,
    val color: String
)
