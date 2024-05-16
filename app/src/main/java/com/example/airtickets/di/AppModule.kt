package com.example.airtickets.di

import com.example.airtickets.ui.features.airticket.AirTicketMapper
import com.example.airtickets.ui.features.airticket.all.AirTicketAllViewModel
import com.example.airtickets.ui.features.airticket.main.AirTicketMainViewModel
import com.example.airtickets.ui.features.airticket.searchcountry.AirTicketSearchCountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AirTicketMainViewModel(get(), get(), get()) }
    viewModel { AirTicketSearchCountryViewModel(get(), get()) }
    viewModel { AirTicketAllViewModel(get(), get()) }
    single { AirTicketMapper() }
}