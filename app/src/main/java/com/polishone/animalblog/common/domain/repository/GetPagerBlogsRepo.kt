package com.polishone.animalblog.common.domain.repository

import com.polishone.animalblog.common.constant.Resource
import com.polishone.animalblog.common.domain.model.entity.AniBlogEntity

interface GetPagerBlogsRepo {

    suspend fun getPagerBlogs(page: Int, limit:Int): Resource<List<AniBlogEntity>>
}