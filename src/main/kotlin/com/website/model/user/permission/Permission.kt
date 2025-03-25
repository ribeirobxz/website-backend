package com.website.model.user.permission

import kotlinx.serialization.Serializable

@Serializable
data class Permission(
    val id: String,
    val name: String,
    val description: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Permission

        if (id != other.id) return false
        if (name != other.name) return false
        if (!description.contentEquals(other.description)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.contentHashCode()
        return result
    }
}
