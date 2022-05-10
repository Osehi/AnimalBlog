package com.polishone.animalblog.common.data.network.model

data class AnimalBlogsDTO(
    val `data`: List<Data>,
    val limit: Int,
    val page: Int,
    val total: Int
)