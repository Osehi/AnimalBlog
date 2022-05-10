package com.polishone.animalblog.common.data.network.model

data class Data(
    val id: String,
    val image: String,
    val likes: Int,
    val owner: Owner,
    val publishDate: String,
    val tags: List<String>,
    val text: String
)