package com.gonexwind.core.data.local.room

import androidx.room.*
import com.gonexwind.core.data.local.entity.HotelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {

    @Query("SELECT * FROM hotel")
    fun getAllHotel(): Flow<List<HotelEntity>>

    @Query("SELECT * FROM hotel where isFavorite = 1")
    fun getFavoriteHotel(): Flow<List<HotelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(tourism: List<HotelEntity>)

    @Update
    fun updateFavoriteHotel(tourism: HotelEntity)
}