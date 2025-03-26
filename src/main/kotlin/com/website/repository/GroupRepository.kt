package com.website.repository

import com.website.model.user.group.Group

interface GroupRepository {

    suspend fun findById(groupId: String): Group?
    suspend fun findAll(): List<Group?>

    suspend fun save(group: Group)
    suspend fun update(group: Group)

    suspend fun delete(group: Group)
    suspend fun deleteAll()

}