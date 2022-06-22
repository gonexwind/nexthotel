package com.gonexwind.core.data.remote

import android.util.Log
import com.gonexwind.core.data.remote.network.ApiResponse
import com.gonexwind.core.data.remote.network.ApiService
import com.gonexwind.core.data.remote.response.HotelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllHotel(): Flow<ApiResponse<List<HotelResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}