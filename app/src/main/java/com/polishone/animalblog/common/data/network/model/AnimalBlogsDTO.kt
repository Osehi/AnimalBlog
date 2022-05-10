package com.polishone.animalblog.common.data.network.model

data class AnimalBlogsDTO(
    val `data`: List<AniBlogDTO>,
    val limit: Int,
    val page: Int,
    val total: Int
)