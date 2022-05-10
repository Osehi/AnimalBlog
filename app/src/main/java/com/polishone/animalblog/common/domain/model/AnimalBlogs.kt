package com.polishone.animalblog.common.domain.model



data class AnimalBlogs(
    val `data`: List<AniBlog>,
    val limit: Int,
    val page: Int,
    val total: Int
)
