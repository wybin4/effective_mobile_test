package com.example.airtickets.ui.models

interface AdaptiveInt {
    val id: Int
    fun areContentsTheSame(other: AdaptiveInt): Boolean {
        return this == other
    }
}