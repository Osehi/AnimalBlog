package com.polishone.animalblog.common.data.network.api

import com.polishone.animalblog.common.data.network.model.AnimalBlogsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("post")
    suspend fun getBlogs(@Header("app-id") id: String): Response<AnimalBlogsDTO>
}