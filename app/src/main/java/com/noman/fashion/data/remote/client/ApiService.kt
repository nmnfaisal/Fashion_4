package com.noman.fashion.data.remote.client

import com.noman.fashion.data.remote.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("smart")
    suspend fun getProductList(
    ): Response<List<ProductResponse>>

}