package com.gonexwind.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListHotelResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<HotelResponse>,
)
