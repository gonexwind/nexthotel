package com.gonexwind.core.domain.repository

import com.gonexwind.core.data.Resource
import com.gonexwind.core.domain.model.Hotel
import kotlinx.coroutines.flow.Flow

interface IHotelRepository {
    fun getAllHotel(): Flow<Resource<List<Hotel>>>
    fun getFavoriteHotel(): Flow<List<Hotel>>
    fun setFavoriteHotel(tourism: Hotel, state: Boolean)
}