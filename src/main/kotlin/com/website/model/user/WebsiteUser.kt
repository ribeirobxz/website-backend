package com.website.model.user

import com.website.cache.group.GroupCache
import com.website.model.user.group.Group
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class WebsiteUser(
    val uniqueId: @Contextual UUID = UUID.randomUUID(),
    val playerName: String,
    var passWord: String,
    var groups: MutableList<String>
) {

    val highestGroup
        get() = groups.mapNotNull { GroupCache.getGroup(it) }
            .minByOrNull { it.id }

    fun hasGroup(groupId: String): Boolean = groups.contains(groupId)
    fun hasGroup(group: Group): Boolean = hasGroup(group.id)

    fun addGroup(group: Group): Boolean {
        return groups.add(group.id)
    }

    fun removeGroup(group: Group): Boolean {
        return groups.remove(group.id)
    }
}
