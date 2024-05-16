package com.example.airtickets.ui.models.state

import androidx.lifecycle.MutableLiveData
import com.example.airtickets.ui.models.networkresult.NetworkResult

fun <T> NetworkResult<List<T>>.asListState(
    state: MutableLiveData<ListState>,
    onSuccess: (data: List<T>) -> Unit
) {
    when (this) {
        is NetworkResult.Success -> {
            val data = this.data
            if (data.isNotEmpty()) {
                state.value = ListState.Success
                onSuccess(data)
            } else {
                state.value = ListState.Empty
            }
        }
        is NetworkResult.Loading -> {
            state.value = ListState.Loading
        }
        is NetworkResult.Error -> {
            val errorMessage = this.exception.message
            if (errorMessage != null) {
                state.value = ListState.Error(errorMessage)
            }
        }
    }
}
