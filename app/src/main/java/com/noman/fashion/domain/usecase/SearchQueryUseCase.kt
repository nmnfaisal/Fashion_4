package com.noman.fashion.domain.usecase

import com.noman.fashion.domain.model.Product
import javax.inject.Inject


class SearchQueryUseCase @Inject constructor() {
    operator fun invoke(query: String, list: List<Product>): List<Product> {
        return list.filter { movie -> movie.title!!.contains(query, ignoreCase = true) }
    }
}