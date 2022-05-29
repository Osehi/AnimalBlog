package com.polishone.animalblog.common.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("blogkey")
data class BlogKey(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var prev: Int,
    var next: Int?
)
