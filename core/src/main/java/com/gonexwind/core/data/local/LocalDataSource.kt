package com.gonexwind.core.data.local

import com.gonexwind.core.data.local.entity.HotelEntity
import com.gonexwind.core.data.local.room.HotelDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val hotelDao: HotelDao) {

    fun getAllHotel(): Flow<List<HotelEntity>> = hotelDao.getAllHotel()

    fun getFavoriteHotel(): Flow<List<HotelEntity>> = hotelDao.getFavoriteHotel()

    suspend fun insertHotel(hotelList: List<HotelEntity>) = hotelDao.insertHotel(hotelList)

    fun setFavoriteHotel(hotel: HotelEntity, newState: Boolean) {
        hotel.isFavorite = newState
        hotelDao.updateFavoriteHotel(hotel)
    }
}