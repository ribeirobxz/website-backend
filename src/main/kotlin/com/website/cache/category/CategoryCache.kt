package com.website.cache.category

import com.github.benmanes.caffeine.cache.Caffeine
import com.website.model.category.Category
import com.website.model.category.NewsCategory

object CategoryCache {

    private val cache = Caffeine.newBuilder()
        .build<String, Category>()

    init {
        cache.put(NewsCategory.id, NewsCategory)
    }

    fun getCategory(categoryId: String): Category? {
        return cache.getIfPresent(categoryId)
    }
}