package com.gonexwind.core.data.remote.network

import com.gonexwind.core.data.remote.response.ListHotelResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list-hotels?limit=10")
    suspend fun getList(): ListHotelResponse
}