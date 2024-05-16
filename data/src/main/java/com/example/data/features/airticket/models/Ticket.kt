package com.example.data.features.airticket.models

data class Ticket(
    val id: Int,
    val badge: String?,
    val price: Price,
    val provider_name: String,
    val company: String,
    val departure: TicketDetails,
    val arrival: TicketDetails,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val luggage: Luggage,
    val hand_luggage: HandLuggage,
    val is_returnable: Boolean,
    val is_exchangable: Boolean
)