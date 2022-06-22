package com.gonexwind.core.data

import com.gonexwind.core.data.local.LocalDataSource
import com.gonexwind.core.data.remote.RemoteDataSource
import com.gonexwind.core.data.remote.network.ApiResponse
import com.gonexwind.core.data.remote.response.HotelResponse
import com.gonexwind.core.domain.model.Hotel
import com.gonexwind.core.domain.repository.IHotelRepository
import com.gonexwind.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class HotelRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IHotelRepository {

    override fun getAllHotel(): Flow<Resource<List<Hotel>>> =
        object : NetworkBoundResource<List<Hotel>, List<HotelResponse>>() {
            override fun loadFromDB(): Flow<List<Hotel>> {
                return localDataSource.getAllHotel().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Hotel>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<HotelResponse>>> =
                remoteDataSource.getAllHotel()

            override suspend fun saveCallResult(data: List<HotelResponse>) {
                val hotelList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertHotel(hotelList)
            }
        }.asFlow()

    override fun getFavoriteHotel(): Flow<List<Hotel>> {
        return localDataSource.getFavoriteHotel().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteHotel(it: Hotel, state: Boolean) {
        val hotelEntity = DataMapper.mapDomainToEntity(it)
        runBlocking {
            withContext(Dispatchers.IO) {
                localDataSource.setFavoriteHotel(hotelEntity, state)
            }
        }
    }
}