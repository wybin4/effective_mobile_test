package com.example.data.features.airticket.dtos

import com.example.data.features.airticket.models.TicketOffer

data class TicketOfferListResponse(
    val tickets_offers: List<TicketOffer>
)