package com.noman.fashion.domain.usecase

import com.noman.fashion.domain.repository.ProductRepository
import com.noman.fashion.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ProductListUseCase
@Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke() = flow {
        emit(Resource.Loading(isLoading = true))
        val response = productRepository.refreshProduct()
        if (response.error.isNullOrEmpty())
            emit(Resource.Success(data = response.data))
        else emit(Resource.Error(error = response.error))
    }
}