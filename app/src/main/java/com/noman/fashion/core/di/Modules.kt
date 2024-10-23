package com.noman.fashion.core.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.noman.fashion.data.local.dao.ProductDao
import com.noman.fashion.data.local.database.AppDatabase
import com.noman.fashion.data.remote.client.ApiService
import com.noman.fashion.data.remote.client.RetrofitClient
import com.noman.fashion.data.repository.ProductRepositoryImpl
import com.noman.fashion.domain.repository.ProductRepository
import com.noman.fashion.domain.usecase.ProductListUseCase
import com.noman.fashion.domain.usecase.SearchQueryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    @Singleton
    fun providesRetrofitClient(): Retrofit {
        return RetrofitClient.retrofit
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDao(
        @ApplicationContext appContext
        : Context
    ): AppDatabase {
        val dbInstance = Room.databaseBuilder<AppDatabase>(
            appContext,
            AppDatabase::class.java,
            "ProductDatabase"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        return dbInstance
    }

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }

    @Provides
    fun provideProductListUseCase(productRepository: ProductRepository): ProductListUseCase =
        ProductListUseCase(productRepository)

    @Provides
    fun provideSearchUseCase(): SearchQueryUseCase = SearchQueryUseCase()

    @Provides
    fun provideProductRepository(
        apiService: ApiService,
        productDao: ProductDao,
    ): ProductRepository = ProductRepositoryImpl(apiService, productDao)


}