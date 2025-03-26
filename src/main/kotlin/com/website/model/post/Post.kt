package com.website.model.post

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date
import java.util.UUID

@Serializable
data class Post(
    val id: @Contextual UUID = UUID.randomUUID(),
    val categoryId: String,
    val senderUniqueId: @Contextual UUID,
    val message: String,
    val createdAt: Long = Date().time
)
