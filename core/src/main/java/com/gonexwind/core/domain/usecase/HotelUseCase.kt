package com.gonexwind.core.domain.usecase

import com.gonexwind.core.data.Resource
import com.gonexwind.core.domain.model.Hotel
import kotlinx.coroutines.flow.Flow

interface HotelUseCase {
    fun getAllHotel(): Flow<Resource<List<Hotel>>>
    fun getFavoriteHotel(): Flow<List<Hotel>>
    fun setFavoriteHotel(tourism: Hotel, state: Boolean)
}