package com.website.repository

import com.website.model.user.WebsiteUser

interface WebsiteUserRepository {

    suspend fun findByName(playerName: String): WebsiteUser?
    suspend fun findAll(): List<WebsiteUser?>

    suspend fun save(websiteUser: WebsiteUser)
    suspend fun update(websiteUser: WebsiteUser)

    suspend fun delete(websiteUser: WebsiteUser)
    suspend fun deleteAll()

}