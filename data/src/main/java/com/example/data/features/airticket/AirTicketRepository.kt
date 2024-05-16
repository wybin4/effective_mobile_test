package com.example.data.features.airticket

import com.example.data.features.airticket.models.Offer
import com.example.data.features.airticket.models.Ticket
import com.example.data.features.airticket.models.TicketOffer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AirTicketRepository(private val airTicketApiService: AirTicketApiService) {
    fun listOffer(): Flow<List<Offer>> = flow {
        emit(airTicketApiService.listOffer().offers)
    }

    fun listTicketOffer(): Flow<List<TicketOffer>> = flow {
        emit(airTicketApiService.listTicketOffer().tickets_offers)
    }

    fun listTicket(): Flow<List<Ticket>> = flow {
        emit(airTicketApiService.listTicket().tickets)
    }
}