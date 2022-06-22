package com.gonexwind.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gonexwind.core.domain.usecase.HotelUseCase

class FavoriteViewModel(hotelUseCase: HotelUseCase) : ViewModel() {
    val favoriteHotel = hotelUseCase.getFavoriteHotel().asLiveData()
}