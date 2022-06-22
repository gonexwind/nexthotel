package com.gonexwind.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gonexwind.core.data.local.entity.HotelEntity

@Database(entities = [HotelEntity::class], version = 1, exportSchema = false)
abstract class HotelDatabase : RoomDatabase() {
    abstract fun hotelDao(): HotelDao
}