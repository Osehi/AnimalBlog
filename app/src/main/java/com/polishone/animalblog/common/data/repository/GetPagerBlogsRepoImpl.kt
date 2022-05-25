package com.polishone.animalblog.common.data.repository

import com.polishone.animalblog.common.constant.NetworkConstant
import com.polishone.animalblog.common.constant.Resource
import com.polishone.animalblog.common.data.mappers.toDomain
import com.polishone.animalblog.common.data.mappers.toDomainEntity
import com.polishone.animalblog.common.data.network.api.ApiService
import com.polishone.animalblog.common.domain.model.entity.AniBlogEntity
import com.polishone.animalblog.common.domain.repository.GetPagerBlogsRepo
import javax.inject.Inject

class GetPagerBlogsRepoImpl @Inject constructor(private val apiService: ApiService): GetPagerBlogsRepo {

    override suspend fun getPagerBlogs(page: Int, limit: Int): Resource<List<AniBlogEntity>>? {
        return try {
            val response = apiService.getBlogsPagination(NetworkConstant.APP_ID, page = page, limit = limit)
            if (response.isSuccessful){
                val body = response.body()!!.data.toDomainEntity()
                Resource.Success(body)
            } else {
                response.errorBody()?.let { Resource.Error(it.string()) }
            }
        } catch (e: Exception){
            Resource.Error(e.localizedMessage)
        }
    }
}