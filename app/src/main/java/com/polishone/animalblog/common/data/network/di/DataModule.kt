package com.polishone.animalblog.common.data.network.di

import android.content.Context
import com.polishone.animalblog.common.constant.NetworkConstant
import com.polishone.animalblog.common.data.cache.BlogDAO
import com.polishone.animalblog.common.data.cache.BlogDatabase
import com.polishone.animalblog.common.data.network.api.ApiService
import com.polishone.animalblog.common.data.repository.AniBlogRepositoryImpl
import com.polishone.animalblog.common.data.repository.GetPagerBlogsRepoImpl
import com.polishone.animalblog.common.domain.repository.AniBlogRepository
import com.polishone.animalblog.common.domain.repository.GetPagerBlogsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    }

    @Provides
    @Singleton
    fun provideClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * provide the repository
     */
    @Provides
    @Singleton
    fun provideGetAniBlogsRepository(apiService: ApiService): AniBlogRepository {
        return AniBlogRepositoryImpl(apiService)
    }

    /**
     * provide database
     */
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BlogDatabase {
        return BlogDatabase.getInstance(context)
    }

    /**
     * provide dao
     */
    @Provides
    fun provideDAO(blogDatabase: BlogDatabase): BlogDAO {
        return blogDatabase.getBlogDAO()
    }

    @Provides
    fun provideGetPagerRepo(apiService: ApiService): GetPagerBlogsRepo {
        return GetPagerBlogsRepoImpl(apiService)
    }
}