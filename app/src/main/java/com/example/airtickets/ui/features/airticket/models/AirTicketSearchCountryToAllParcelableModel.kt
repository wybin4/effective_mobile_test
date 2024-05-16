package com.example.airtickets.ui.features.airticket.models

import android.os.Bundle
import android.os.Parcel
import com.example.airtickets.ui.models.parcel.ParcelableModel
import com.example.airtickets.ui.models.parcel.readDate
import com.example.airtickets.ui.models.parcel.writeDate
import com.example.airtickets.ui.utils.converters.DateTimeConverter
import java.util.Date

class AirTicketSearchCountryToAllParcelableModel(
    val source: String,
    val sourceDate: Date?,
    val destDate: Date?,
    val dest: String,
    val passengerCount: Int
) : ParcelableModel(), DateTimeConverter {
    constructor(parcel: Parcel) : this(
        source = parcel.readString() ?: "",
        sourceDate = parcel.readDate(),
        destDate = parcel.readDate(),
        dest = parcel.readString() ?: "",
        passengerCount = parcel.readInt(),
    )

    fun toBundle(): Bundle {
        return Bundle().apply {
            putParcelable("sourceDest", this@AirTicketSearchCountryToAllParcelableModel)
        }
    }

    fun getSourceDashDest(): String {
        return "$source-$dest"
    }

    private fun formatSource(): String {
        return getDMMMMWithYearIfNeeded(sourceDate ?: Date())
    }

    private fun pluralizePassenger(): String {
        val singularForm = "пассажир"
        val pluralForm1 = "пассажира"
        val pluralForm2 = "пассажиров"

        return when {
            passengerCount % 10 == 1 && passengerCount % 100 != 11 -> "$passengerCount $singularForm"
            passengerCount % 10 in 2..4 && (passengerCount % 100 < 10 || passengerCount % 100 >= 20) -> "$passengerCount $pluralForm1"
            else -> "$passengerCount $pluralForm2"
        }
    }

    fun formatSourceAndPassCount(): String {
        return "${formatSource()}, ${pluralizePassenger()}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(passengerCount)
        parcel.writeString(source)
        parcel.writeDate(sourceDate)
        parcel.writeDate(destDate)
        parcel.writeString(dest)
    }
}