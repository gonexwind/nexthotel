package com.gonexwind.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hotel(
    val id: Int,
    val name: String,
    val city: String,
    val imageUrl: String,
    val rate: Double,
    val stars: Int,
    val description: String,
    val priceRange: String,
    val reviews: String,
    val isFavorite: Boolean,
) : Parcelable