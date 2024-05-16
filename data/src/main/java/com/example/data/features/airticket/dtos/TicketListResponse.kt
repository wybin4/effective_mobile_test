package com.example.data.features.airticket.dtos

import com.example.data.features.airticket.models.Ticket

data class TicketListResponse(
    val tickets: List<Ticket>
)