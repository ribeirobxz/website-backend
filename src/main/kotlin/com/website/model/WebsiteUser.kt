package com.website.model

import kotlinx.serialization.Serializable

@Serializable
data class WebsiteUser(
    val playerName: String,
    var passWord: String
)
