package com.example.airtickets.di

import com.example.airtickets.ui.airticket.AirTicketMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        AirTicketMainViewModel()
    }

}