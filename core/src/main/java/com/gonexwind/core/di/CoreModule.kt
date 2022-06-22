package com.gonexwind.core.di

import androidx.room.Room
import com.gonexwind.core.data.HotelRepository
import com.gonexwind.core.data.local.LocalDataSource
import com.gonexwind.core.data.local.room.HotelDatabase
import com.gonexwind.core.data.remote.RemoteDataSource
import com.gonexwind.core.data.remote.network.ApiService
import com.gonexwind.core.domain.repository.IHotelRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nexthotel-backend-b7iyywuv5a-as.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory {
        get<HotelDatabase>().hotelDao()
    }
    single {
        Room.databaseBuilder(androidContext(), HotelDatabase::class.java, "Hotel.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IHotelRepository> { HotelRepository(get(), get()) }
}