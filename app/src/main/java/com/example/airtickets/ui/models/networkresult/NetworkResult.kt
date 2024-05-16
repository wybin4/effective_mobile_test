package com.example.airtickets.ui.models.networkresult

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val exception: Throwable) : NetworkResult<Nothing>
    data object Loading : NetworkResult<Nothing>
}