package com.example.airtickets.ui.models.networkresult

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asNetworkResult(): Flow<NetworkResult<T>> = map<T, NetworkResult<T>> {
    NetworkResult.Success(
        it
    )
}
    .onStart { emit(NetworkResult.Loading) }
    .catch { emit(NetworkResult.Error(it)) }