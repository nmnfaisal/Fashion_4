package com.noman.fashion.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Product (

    var id          : Int,
    var title       : String?,
    var isNew       : Boolean?,
    var oldPrice    : String?,
    var price       : Float?,
    var description : String?,
    var category    : String?,
    var image       : String?,
    var rating      : Int?,
    var quantity    : Int?

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeValue(isNew)
        parcel.writeString(oldPrice)
        parcel.writeValue(price)
        parcel.writeString(description)
        parcel.writeString(category)
        parcel.writeString(image)
        parcel.writeValue(rating)
        parcel.writeValue(quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}