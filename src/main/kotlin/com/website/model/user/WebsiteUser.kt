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
    var groups: Array<Group>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WebsiteUser

        if (uniqueId != other.uniqueId) return false
        if (playerName != other.playerName) return false
        if (passWord != other.passWord) return false
        if (!groups.contentEquals(other.groups)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uniqueId.hashCode()
        result = 31 * result + playerName.hashCode()
        result = 31 * result + passWord.hashCode()
        result = 31 * result + groups.contentHashCode()
        return result
    }

}
