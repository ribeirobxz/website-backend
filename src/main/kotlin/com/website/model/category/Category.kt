package com.website.model.category

sealed interface Category {

    val id: String

    val name: String
    val description: Array<String>

}

data object NewsCategory : Category {
    override val id: String = "news"
    override val name: String = "Novidades"
    override val description: Array<String> = arrayOf(
        "",
        ""
    )

}