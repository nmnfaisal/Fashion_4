package com.noman.fashion.data.repository

import android.content.Context
import com.noman.fashion.data.local.dao.ProductDao
import com.noman.fashion.data.remote.client.ApiService
import com.noman.fashion.data.remote.dto.ProductListDto
import com.noman.fashion.data.toDomain
import com.noman.fashion.data.toEntity
import com.noman.fashion.domain.model.Product
import com.noman.fashion.domain.repository.ProductRepository
import com.noman.fashion.utils.ConnectivityObserver
import com.noman.fashion.utils.NetworkConnectivityObserver
import com.noman.fashion.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl
@Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao
) : ProductRepository {

    private lateinit var connectivityObserver: ConnectivityObserver
    override suspend fun getProductsFromApi(): Response<ProductListDto> {
        return apiService.getProductList()
    }

    override suspend fun searchProduct(searchQuery: String): Response<ProductListDto> {
        TODO("Not yet implemented")
    }

//    override suspend fun searchProduct(searchQuery: String): Response<ProductListDto> {
//        return apiService.searchMovies(searchQuery)
//    }

    override fun observeConnectivity(context: Context): Flow<ConnectivityObserver.Status> {
        connectivityObserver = NetworkConnectivityObserver(context)
        return connectivityObserver.observe()
    }

    override suspend fun refreshProduct(): Resource<List<Product>> {
//        if (!connectivityManager.isNetworkConnected()) {
//            val list = movieDao.getAllMovies()
//            return if (list.isNotEmpty()) {
//                Resource.Success(
//                    data = list
//                        .map { movieEntity -> movieEntity.toDomain() })
//            } else {
//                Resource.Error(error = "No internet connection available.")
//            }
//        }
        try {
            val products = apiService.getProductList()
            if (products.isSuccessful)
                products.body()?.let {
                    productDao.insertProducts(it.map { productListDtoItem ->
                        productListDtoItem.toEntity()
                    })
                }
            else return Resource.Error(error = products.message())
        } catch (exception: Exception) {
            return Resource.Error(error = exception.message ?: "Something went wrong")
        }
        return Resource.Success(
//            data = products
            data = productDao.getAllProducts().map { productEntity -> productEntity.toDomain() })
    }
}