package com.example.data.features.airticket.dtos

import com.example.data.features.airticket.models.Offer

data class OfferListResponse(
    val offers: List<Offer>
)