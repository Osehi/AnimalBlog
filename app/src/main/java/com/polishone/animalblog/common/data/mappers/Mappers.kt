package com.polishone.animalblog.common.data.mappers

import com.polishone.animalblog.common.data.network.model.AniBlogDTO
import com.polishone.animalblog.common.data.network.model.OwnerDTO
import com.polishone.animalblog.common.domain.model.AniBlog
import com.polishone.animalblog.common.domain.model.Owner

fun List<AniBlogDTO>.toDomain(): List<AniBlog> {
    return map {
        AniBlog(
            id = it.id,
            image = it.image,
            likes = it.likes,
            owner = it.owner.toDomain(),
            publishDate = it.publishDate,
            tags = it.tags,
            text = it.text
        )
    }
}

fun OwnerDTO.toDomain(): Owner {
    return Owner(
        firstName, id, lastName, picture, title
    )
}