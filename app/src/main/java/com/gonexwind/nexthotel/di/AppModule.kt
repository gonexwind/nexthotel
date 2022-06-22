package com.gonexwind.nexthotel.di

import com.gonexwind.core.domain.usecase.HotelInteractor
import com.gonexwind.core.domain.usecase.HotelUseCase
import com.gonexwind.nexthotel.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<HotelUseCase> { HotelInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}