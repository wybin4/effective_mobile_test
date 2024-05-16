package com.example.data.features.airticket

import com.example.data.features.airticket.dtos.OfferListResponse
import com.example.data.features.airticket.dtos.TicketListResponse
import com.example.data.features.airticket.dtos.TicketOfferListResponse
import retrofit2.http.GET

interface AirTicketApiService {
    @GET("214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun listOffer(): OfferListResponse

    @GET("7e55bf02-89ff-4847-9eb7-7d83ef884017")
    suspend fun listTicketOffer(): TicketOfferListResponse

    @GET("670c3d56-7f03-4237-9e34-d437a9e56ebf")
    suspend fun listTicket(): TicketListResponse
}