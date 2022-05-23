package com.polishone.animalblog.common.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.polishone.animalblog.common.domain.model.Owner
@Entity("aniblog")
data class AniBlogEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String,
    val likes: Int,
    val owner: Owner,
    val publishDate: String,
    val tags: List<String>,
    val text: String
)
