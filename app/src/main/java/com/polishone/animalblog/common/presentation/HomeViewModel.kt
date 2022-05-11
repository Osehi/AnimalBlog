package com.polishone.animalblog.common.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polishone.animalblog.common.constant.Resource
import com.polishone.animalblog.common.domain.usecase.GetAniBlogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAniBlogsUseCase: GetAniBlogsUseCase) : ViewModel() {

    private val _aniBlog:MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val aniBlog:StateFlow<HomeState> = _aniBlog


    fun getAniBlog(){
        viewModelScope.launch {
            getAniBlogsUseCase().onEach {
                when(it){
                    is Resource.Loading -> {
                        _aniBlog.value = HomeState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _aniBlog.value = HomeState(data = it.data)
                    }
                    is Resource.Error -> {
                        _aniBlog.value = HomeState(error = it.message.toString())
                    }
                }
            }
        }
    }
}