package com.gonexwind.core.domain.usecase

import com.gonexwind.core.domain.model.Hotel
import com.gonexwind.core.domain.repository.IHotelRepository

class HotelInteractor(private val hotelRepository: IHotelRepository) :
    HotelUseCase {

    override fun getAllHotel() = hotelRepository.getAllHotel()

    override fun getFavoriteHotel() = hotelRepository.getFavoriteHotel()

    override fun setFavoriteHotel(it: Hotel, state: Boolean) =
        hotelRepository.setFavoriteHotel(it, state)
}