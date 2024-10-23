package com.noman.fashion.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noman.fashion.data.local.dao.ProductDao
import com.noman.fashion.data.remote.dto.ProductEntity
import javax.inject.Singleton

@Database(entities = [ProductEntity::class], version = 1)
@Singleton

abstract class AppDatabase : RoomDatabase(){

    abstract fun productDao(): ProductDao
}