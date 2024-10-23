package com.noman.fashion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noman.fashion.data.remote.dto.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAllProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<ProductEntity>)

}