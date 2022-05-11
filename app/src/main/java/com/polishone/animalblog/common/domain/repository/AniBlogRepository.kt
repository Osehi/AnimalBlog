package com.polishone.animalblog.common.domain.repository

import com.polishone.animalblog.common.domain.model.AniBlog
import retrofit2.Response

interface AniBlogRepository {

    suspend fun getAniBlogs(): List<AniBlog>

}