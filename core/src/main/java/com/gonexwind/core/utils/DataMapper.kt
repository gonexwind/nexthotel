package com.gonexwind.core.utils

import com.gonexwind.core.data.local.entity.HotelEntity
import com.gonexwind.core.data.remote.response.HotelResponse
import com.gonexwind.core.domain.model.Hotel

object DataMapper {
    fun mapResponsesToEntities(input: List<HotelResponse>): List<HotelEntity> {
        val hotelList = ArrayList<HotelEntity>()
        input.map {
            val hotel = HotelEntity(
                id = it.id,
                name = it.name,
                city = it.city,
                imageUrl = it.imageUrl,
                rate = it.rate,
                stars = it.stars,
                description = it.description,
                priceRange = it.priceRange,
                reviews = it.reviews,
                isFavorite = false
            )
            hotelList.add(hotel)
        }
        return hotelList
    }

    fun mapEntitiesToDomain(input: List<HotelEntity>): List<Hotel> =
        input.map {
            Hotel(
                id = it.id,
                name = it.name,
                city = it.city,
                imageUrl = it.imageUrl,
                rate = it.rate,
                stars = it.stars,
                description = it.description,
                priceRange = it.priceRange,
                reviews = it.reviews,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Hotel) = HotelEntity(
        id = input.id,
        name = input.name,
        city = input.city,
        imageUrl = input.imageUrl,
        rate = input.rate,
        stars = input.stars,
        description = input.description,
        priceRange = input.priceRange,
        reviews = input.reviews,
        isFavorite = input.isFavorite
    )
}