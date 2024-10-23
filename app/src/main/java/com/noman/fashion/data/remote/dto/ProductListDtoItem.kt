package com.noman.fashion.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductListDtoItem(
    @SerializedName("_id"         ) var id          : Int     ,
    @SerializedName("title"       ) var title       : String?  = null,
    @SerializedName("isNew"       ) var isNew       : Boolean? = null,
    @SerializedName("oldPrice"    ) var oldPrice    : String?  = null,
    @SerializedName("price"       ) var price       : Float?    = null,
    @SerializedName("description" ) var description : String?  = null,
    @SerializedName("category"    ) var category    : String?  = null,
    @SerializedName("image"       ) var image       : String?  = null,
    @SerializedName("rating"      ) var rating      : Int?     = null,
    @SerializedName("quantity"    ) var quantity    : Int?     = null,
)