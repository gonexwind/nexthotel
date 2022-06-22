package com.gonexwind.nexthotel.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gonexwind.core.domain.model.Hotel
import com.gonexwind.core.domain.usecase.HotelUseCase

class HomeViewModel(private val hotelUseCase: HotelUseCase) : ViewModel() {
    val hotel = hotelUseCase.getAllHotel().asLiveData()

    fun setFavoriteTourism(hotel: Hotel, state: Boolean) =
        hotelUseCase.setFavoriteHotel(hotel, state)
}