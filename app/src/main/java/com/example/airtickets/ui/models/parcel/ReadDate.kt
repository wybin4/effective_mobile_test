package com.example.airtickets.ui.models.parcel

import android.os.Parcel
import java.util.Date

fun Parcel.readDate(): Date? {
    return readLong().let { time ->
        if (time != 0L) {
            Date(time)
        } else {
            null
        }
    }
}