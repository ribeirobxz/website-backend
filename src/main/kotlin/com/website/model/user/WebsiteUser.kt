package com.website.model.user

import com.website.model.user.group.Group
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class WebsiteUser(
    val uniqueId: @Contextual UUID = UUID.randomUUID(),
    val playerName: String,
    var passWord: String,
    var group: Group
)
