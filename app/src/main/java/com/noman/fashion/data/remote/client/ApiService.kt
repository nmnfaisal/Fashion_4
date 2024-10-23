package com.noman.fashion.data.remote.client

import com.noman.fashion.data.remote.dto.ProductEntity
import com.noman.fashion.data.remote.dto.ProductListDto
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("smart")
    suspend fun getProductList(
    ): Response<ProductListDto>

}