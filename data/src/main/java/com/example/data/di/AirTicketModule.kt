package com.example.data.di

import com.example.data.features.airticket.AirTicketRepository
import org.koin.dsl.module

val airTicketModule = module { single<AirTicketRepository> { AirTicketRepository(get()) } }