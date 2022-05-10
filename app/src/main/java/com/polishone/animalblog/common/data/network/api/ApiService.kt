package com.polishone.animalblog.common.data.network.api

import com.polishone.animalblog.common.data.network.model.AnimalBlogsDTO
import retrofit2.Response

interface ApiService {

    suspend fun getBlogs(): Response<AnimalBlogsDTO>
}