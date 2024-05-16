package com.example.airtickets.ui.features.airticket

import com.example.airtickets.ui.features.airticket.models.OfferUiModel
import com.example.airtickets.ui.features.airticket.models.TicketOfferUiModel
import com.example.airtickets.ui.features.airticket.models.TicketUiModel
import com.example.airtickets.ui.utils.converters.DateTimeConverter
import com.example.data.features.airticket.models.Offer
import com.example.data.features.airticket.models.Ticket
import com.example.data.features.airticket.models.TicketOffer

class AirTicketMapper: DateTimeConverter {
    fun offerListToUi(offerList: List<Offer>): List<OfferUiModel> {
        return offerList.map {
            OfferUiModel(
                id = it.id,
                town = it.town,
                title = it.title,
                price = it.price.value
            )
        }
    }

    fun ticketOfferListToUi(ticketOfferList: List<TicketOffer>): List<TicketOfferUiModel> {
        return ticketOfferList.map {
            TicketOfferUiModel(
                id = it.id,
                timeRange = it.time_range,
                title = it.title,
                price = it.price.value
            )
        }
    }

    fun ticketListToUi(ticketList: List<Ticket>): List<TicketUiModel> {
        return ticketList.map {
            TicketUiModel(
                id = it.id,
                departureDate = parseDate(it.departure.date),
                arrivalDate = parseDate(it.arrival.date),
                departureAirport = it.departure.airport,
                arrivalAirport = it.arrival.airport,
                badge = it.badge,
                price = it.price.value,
                hasTransfer = it.has_transfer
            )
        }
    }
}