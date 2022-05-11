package com.polishone.animalblog.common.data.repository

import com.polishone.animalblog.common.data.mappers.toDomain
import com.polishone.animalblog.common.data.network.api.ApiService
import com.polishone.animalblog.common.domain.model.AniBlog
import com.polishone.animalblog.common.domain.repository.AniBlogRepository
import com.polishone.animalblog.common.utils.SafeApiRequest
import retrofit2.Response
import javax.inject.Inject

class AniBlogRepositoryImpl @Inject constructor(
    private val apiService: ApiService
    ): AniBlogRepository , SafeApiRequest(){

    override suspend fun getAniBlogs(): List<AniBlog> {
        val response = safeApiRequest { apiService.getBlogs() }
        return response.data.toDomain()?: emptyList()
    }
}