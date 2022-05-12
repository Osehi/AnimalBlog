package com.polishone.animalblog.common.domain.usecase

import com.polishone.animalblog.common.constant.Resource
import com.polishone.animalblog.common.domain.model.AniBlog
import com.polishone.animalblog.common.domain.repository.AniBlogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAniBlogsUseCase @Inject constructor(private val getAniBlogRepository: AniBlogRepository) {

     operator fun invoke(): Flow<Resource<List<AniBlog>>> = flow {
        emit(Resource.Loading(null))
        try {
            val response = getAniBlogRepository.getAniBlogs()
            emit(Resource.Success(response))
        } catch (e:Exception){
            emit(Resource.Error("error occurred"))
        }
    }
}