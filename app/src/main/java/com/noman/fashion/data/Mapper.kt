package com.noman.fashion.data

import com.noman.fashion.data.remote.dto.ProductEntity
import com.noman.fashion.data.remote.dto.ProductListDto
import com.noman.fashion.data.remote.dto.ProductListDtoItem
import com.noman.fashion.domain.model.Product


fun ProductListDtoItem.toEntity() = ProductEntity(
    id = this.id,
    title = this.title,
    isNew = this.isNew,
    oldPrice = this.oldPrice,
    price = this.price,
    description = this.description,
    category = this.category,
    image = this.image,
    rating = this.rating,
    quantity = this.quantity,
)

fun ProductEntity.toDomain() = Product(
    id = this.id,
    title = this.title,
    isNew = this.isNew,
    oldPrice = this.oldPrice,
    price = this.price,
    description = this.description,
    category = this.category,
    image = this.image,
    rating = this.rating,
    quantity = this.quantity,
)
