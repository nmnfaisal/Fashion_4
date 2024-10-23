package com.noman.fashion.domain.repository

import android.content.Context
import com.noman.fashion.data.remote.dto.ProductListDto
import com.noman.fashion.domain.model.Product
import com.noman.fashion.utils.ConnectivityObserver
import com.noman.fashion.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface ProductRepository {

//    suspend fun getMovieDetails(movieID: String): com.noman.movie.utils.Result<MovieDetails>
//    fun getAllMoviesFromDataBase(): List<Movie>

    suspend fun getProductsFromApi(): Response<ProductListDto>

    suspend fun searchProduct(searchQuery: String): Response<ProductListDto>

    fun observeConnectivity(context: Context): Flow<ConnectivityObserver.Status>

    suspend fun refreshProduct(): Resource<List<Product>>

}