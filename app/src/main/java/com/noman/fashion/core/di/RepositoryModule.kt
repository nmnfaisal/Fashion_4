package com.noman.fashion.core.di

import com.noman.fashion.data.repository.ProductRepositoryImpl
import com.noman.fashion.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
annotation class MyRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @MyRepositoryImpl
    @Singleton
    @Binds
    abstract fun bindProductRepositoryImpl(
        productRepositoryImpl: ProductRepositoryImpl,
    ): ProductRepository
}