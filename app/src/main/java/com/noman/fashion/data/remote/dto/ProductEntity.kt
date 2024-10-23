package com.noman.fashion.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String?,
    var isNew: Boolean?,
    var oldPrice: String?,
    var price: Float?,
    var description: String?,
    var category: String?,
    var image: String?,
    var rating: Int?,
    var quantity: Int?
)
