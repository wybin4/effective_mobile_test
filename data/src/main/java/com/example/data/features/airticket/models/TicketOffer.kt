package com.example.data.features.airticket.models

data class TicketOffer(
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: Price
)