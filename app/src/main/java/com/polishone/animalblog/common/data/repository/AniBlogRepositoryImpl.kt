package com.polishone.animalblog.common.data.repository

import com.polishone.animalblog.common.domain.model.AniBlog
import com.polishone.animalblog.common.domain.repository.AniBlogRepository
import retrofit2.Response

class AniBlogRepositoryImpl: AniBlogRepository {
    override suspend fun getAniBlogs(): Response<List<AniBlog>> {
        TODO("Not yet implemented")
    }
}