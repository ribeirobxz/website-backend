package com.website.cache.permission

import com.github.benmanes.caffeine.cache.Caffeine
import com.website.model.user.permission.ManageGroupsPermission
import com.website.model.user.permission.Permission

object PermissionCache {

    private val cache = Caffeine.newBuilder()
        .build<String, Permission>()

    init {
        cache.put(ManageGroupsPermission.id, ManageGroupsPermission)
    }

    fun getPermission(permissionId: String): Permission? {
        return cache.getIfPresent(permissionId)
    }
}