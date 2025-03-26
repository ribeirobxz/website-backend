package com.website.model.user.permission

import kotlinx.serialization.Serializable


@Serializable
sealed interface Permission {

    val id: String
    val name: String
    val description: Array<String>

}

data object ManageGroupsPermission : Permission {
    override val id: String = "MANAGE_GROUPS"
    override val name: String = "Administrar Grupos"
    override val description: Array<String> = arrayOf(
        "Gerencie todas as propriedas que",
        "um grupo possui."
    )

}
