package com.gonexwind.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class HotelResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("hotel") val name: String,
    @SerializedName("kota") val city: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("rating") val rate: Double,
    @SerializedName("stars") val stars: Int,
    @SerializedName("description") val description: String,
    @SerializedName("harga") val priceRange: String,
    @SerializedName("reviews") val reviews: String,
)