package com.example.airtickets.ui.features.airticket.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airtickets.ui.features.airticket.AirTicketMapper
import com.example.airtickets.ui.features.airticket.models.AirTicketMainToSearchCountryParcelableModel
import com.example.airtickets.ui.features.airticket.models.OfferUiModel
import com.example.airtickets.ui.models.networkresult.asNetworkResult
import com.example.airtickets.ui.models.state.ListState
import com.example.airtickets.ui.models.state.asListState
import com.example.data.features.airticket.AirTicketRepository
import kotlinx.coroutines.launch

class AirTicketMainViewModel(
    application: Application,
    private val repository: AirTicketRepository,
    private val mapper: AirTicketMapper
) : ViewModel() {
    private val sharedPreferences =
        application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    private val _offerList = MutableLiveData<List<OfferUiModel>>()
    val offerList: LiveData<List<OfferUiModel>> = _offerList

    private val _offerListState = MutableLiveData<ListState>(ListState.Loading)
    val offerListState: LiveData<ListState> = _offerListState

    private val sourceString = "source"
    var destination = ""

    fun createParcel(): AirTicketMainToSearchCountryParcelableModel {
        val source = loadSource()
        return AirTicketMainToSearchCountryParcelableModel(source ?: "", destination)
    }

    fun saveSource(userInput: String) {
        val editor = sharedPreferences.edit()
        editor.putString(sourceString, userInput)
        editor.apply()
    }

    fun loadSource(): String? {
        return sharedPreferences.getString(sourceString, "")
    }

    fun fetchOfferList() {
        viewModelScope.launch {
            repository.listOffer()
                .asNetworkResult()
                .collect {
                    it.asListState(_offerListState) { data ->
                        _offerList.value = mapper.offerListToUi(data)
                    }
                }
        }
    }
}