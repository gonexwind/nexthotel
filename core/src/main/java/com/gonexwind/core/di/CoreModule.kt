package com.gonexwind.core.di

import androidx.room.Room
import com.gonexwind.core.data.HotelRepository
import com.gonexwind.core.data.local.LocalDataSource
import com.gonexwind.core.data.local.room.HotelDatabase
import com.gonexwind.core.data.remote.RemoteDataSource
import com.gonexwind.core.data.remote.network.ApiService
import com.gonexwind.core.domain.repository.IHotelRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val hostname = "nexthotel-backend-b7iyywuv5a-as.a.run.app"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/0IN/uUZcXKnC8clfKXn0Z3skCjWqxizVyWZFUjjo45c")
            .add(hostname, "sha256/zCTnfLwLKbS9S2sbp+uFz4KZOocFvXxkV06Ce9O5M2w")
            .add(hostname, "sha256/hxqRlPTu1bMS/0DITB1SSu0vd4u/8l8TjPgfaAp63Gc")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://nexthotel-backend-b7iyywuv5a-as.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<HotelDatabase>().hotelDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("INDONESIA".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), HotelDatabase::class.java, "Hotel.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IHotelRepository> { HotelRepository(get(), get()) }
}