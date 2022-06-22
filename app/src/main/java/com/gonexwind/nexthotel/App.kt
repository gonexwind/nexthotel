package com.gonexwind.nexthotel

import android.app.Application
import com.gonexwind.core.di.databaseModule
import com.gonexwind.core.di.networkModule
import com.gonexwind.core.di.repositoryModule
import com.gonexwind.nexthotel.di.useCaseModule
import com.gonexwind.nexthotel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}