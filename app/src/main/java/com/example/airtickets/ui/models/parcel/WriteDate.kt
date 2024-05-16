package com.example.airtickets.ui.models.parcel

import android.os.Parcel
import java.util.Date

fun Parcel.writeDate(date: Date?) {
    writeLong(date?.time ?: 0L)
}