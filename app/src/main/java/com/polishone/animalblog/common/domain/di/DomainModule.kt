package com.polishone.animalblog.common.domain.di

import com.polishone.animalblog.common.domain.repository.AniBlogRepository
import com.polishone.animalblog.common.domain.usecase.GetAniBlogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@InstallIn
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetAniBlogsUseCase(getAniBlogRepository: AniBlogRepository): GetAniBlogsUseCase {
        return GetAniBlogsUseCase(getAniBlogRepository)
    }
}