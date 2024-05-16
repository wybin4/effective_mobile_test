package com.example.data.di

import com.example.data.features.airticket.AirTicketApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        val okHttpClient = OkHttpClient.Builder().build()
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(AirTicketApiService::class.java)
    }

}