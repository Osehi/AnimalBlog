package com.polishone.animalblog.common.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.polishone.animalblog.common.constant.Resource
import com.polishone.animalblog.common.data.cache.BlogDAO
import com.polishone.animalblog.common.data.cache.BlogKey
import com.polishone.animalblog.common.domain.model.entity.AniBlogEntity
import com.polishone.animalblog.common.domain.repository.GetPagerBlogsRepo
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BlogRemoteMediator @Inject constructor(
    private val initialPage: Int = 1,
    private val getPagerBlogsRepo: GetPagerBlogsRepo,
    private val blogDAO: BlogDAO
): RemoteMediator<Int, AniBlogEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AniBlogEntity>
    ): MediatorResult {

        return try {
            val page: Int = when(loadType){
                LoadType.APPEND -> {
                    val remoteKeys = getLastKey(state)
                    remoteKeys?.next?: return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestKey(state)
                    remoteKeys?.next?.minus(1)?:initialPage
                }
            }

            val response = getPagerBlogsRepo.getPagerBlogs(page = page, limit = state.config.pageSize)
            val endOfPagination = response?.datas?.size!! < state.config.pageSize

            when(response){
                is Resource.Success -> {
                    val body = response.data

                    if (loadType == LoadType.REFRESH){
                        blogDAO.deleteAllBlogKey()
                        blogDAO.deleteAllItems()
                    }

                    val prev = if(page == initialPage) initialPage else page-1
                    val next = if(endOfPagination) null else page+1

                    val list = body.map {
                        BlogKey(id = it.id, prev, next)
                    }
                    list.let { blogDAO.insertAllBlogKeys(list) }
                    body.let { blogDAO.insertAllBlogs(body) }
                }
                is Resource.Error -> {
                    MediatorResult.Error(Exception())
                }
            }

            if (response is Resource.Success){
                if (endOfPagination){
                    MediatorResult.Success(true)
                }else {
                    MediatorResult.Success(false)
                }
            } else {
                MediatorResult.Success(true)
            }



            return MediatorResult.Success(true)
        } catch (e:Exception){
            MediatorResult.Error(e)
        }
    }

    suspend fun getLastKey(state: PagingState<Int, AniBlogEntity>): BlogKey? {
        return state.lastItemOrNull()?.let {
            blogDAO.getAllKeys(it.id)
        }
    }

    suspend fun getClosestKey(state: PagingState<Int, AniBlogEntity>): BlogKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                blogDAO.getAllKeys(it.id)
            }
        }
    }
}