package com.website.cache.group

import com.github.benmanes.caffeine.cache.Caffeine
import com.website.model.user.group.Group

object GroupCache {

    private val cache = Caffeine.newBuilder()
        .build<String, Group>()

    val values get() = cache.asMap().values

    fun getGroup(groupId: String): Group? {
        return cache.getIfPresent(groupId)
    }

    fun addGroup(group: Group) = cache.put(group.id, group)
    fun removeGroup(groupId: String) = cache.invalidate(groupId)
}