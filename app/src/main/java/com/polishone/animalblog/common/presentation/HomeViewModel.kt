package com.polishone.animalblog.common.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.google.android.material.tabs.TabLayout
import com.polishone.animalblog.common.constant.Resource
import com.polishone.animalblog.common.data.cache.BlogDAO
import com.polishone.animalblog.common.data.paging.BlogRemoteMediator
import com.polishone.animalblog.common.domain.repository.GetPagerBlogsRepo
import com.polishone.animalblog.common.domain.usecase.GetAniBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAniBlogsUseCase: GetAniBlogsUseCase,
    private val getPagerBlogsRepo: GetPagerBlogsRepo,
    private val blogDAO: BlogDAO,
    private val savedStateHandle: SavedStateHandle
    ) : ViewModel() {

    val TAG = "HOMEVIEWMODEL"

    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        remoteMediator = BlogRemoteMediator(getPagerBlogsRepo = getPagerBlogsRepo, blogDAO = blogDAO)){
        blogDAO.getAllBlogItems()
    }.flow.cachedIn(viewModelScope)
    /*
    private val _aniBlog:MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val aniBlog:StateFlow<HomeState> = _aniBlog

     */

    /*
    fun getAniBlog(){

            getAniBlogsUseCase().onEach {
                when(it){
                    is Resource.Loading -> {
                        _aniBlog.value = HomeState(isLoading = true)
                        Log.d("see", "come and see is loading")
                    }
                    is Resource.Success -> {
                        _aniBlog.value = HomeState(data = it.data)
                        Log.d("see", "here is the output: ${it}")
                    }
                    is Resource.Error -> {
                        _aniBlog.value = HomeState(error = it.message.toString())
                        Log.d("see", "the error is ${it.message.toString()}")
                    }
                }
            }.launchIn(viewModelScope)

    }

     */
}