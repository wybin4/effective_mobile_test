package com.example.airtickets.ui.features.airticket.models

import android.os.Bundle
import android.os.Parcel
import com.example.airtickets.ui.models.parcel.ParcelableModel

class AirTicketMainToSearchCountryParcelableModel(
    var source: String,
    var dest: String,
) : ParcelableModel() {
    constructor(parcel: Parcel) : this(
        source = parcel.readString() ?: "",
        dest = parcel.readString() ?: "",
    )

    fun toBundle(): Bundle {
        return Bundle().apply {
            putParcelable("sourceDest", this@AirTicketMainToSearchCountryParcelableModel)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(source)
        parcel.writeString(this.dest)
    }
}