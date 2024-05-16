package com.example.airtickets.ui.features.airticket.searchcountry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airtickets.ui.features.airticket.AirTicketMapper
import com.example.airtickets.ui.features.airticket.models.AirTicketMainToSearchCountryParcelableModel
import com.example.airtickets.ui.features.airticket.models.AirTicketSearchCountryToAllParcelableModel
import com.example.airtickets.ui.features.airticket.models.TicketOfferUiModel
import com.example.airtickets.ui.models.state.ListState
import com.example.airtickets.ui.models.networkresult.asNetworkResult
import com.example.airtickets.ui.models.state.asListState
import com.example.airtickets.ui.utils.converters.DateTimeConverter
import com.example.data.features.airticket.AirTicketRepository
import kotlinx.coroutines.launch
import java.util.Date

class AirTicketSearchCountryViewModel(
    private val repository: AirTicketRepository,
    private val mapper: AirTicketMapper
) : ViewModel(), DateTimeConverter {
    private val _ticketOfferList = MutableLiveData<List<TicketOfferUiModel>>()
    val ticketOfferList: LiveData<List<TicketOfferUiModel>> = _ticketOfferList

    private val _ticketOfferListState = MutableLiveData<ListState>(ListState.Loading)
    val ticketOfferListState: LiveData<ListState> = _ticketOfferListState

    lateinit var parcelable: AirTicketMainToSearchCountryParcelableModel

    var sourceDate: Date? = null
    var destinationDate: Date? = null

    fun createParcel(): AirTicketSearchCountryToAllParcelableModel {
        return AirTicketSearchCountryToAllParcelableModel(
            sourceDate = sourceDate,
            destDate = destinationDate,
            source = parcelable.source,
            dest = parcelable.dest,
            passengerCount = 1 // TODO
        )
    }

    fun formatDate(date: Date): String {
        return getDMMMWithYearIfNeeded(date)
    }

    fun formatDayOfWeek(date: Date): String {
        return getDayOfWeekLowerCase(date)
    }

    fun fetchTicketOfferList() {
        viewModelScope.launch {
            repository.listTicketOffer()
                .asNetworkResult()
                .collect {
                    it.asListState(_ticketOfferListState) { data ->
                        _ticketOfferList.value = mapper.ticketOfferListToUi(data)
                    }
                }
        }
    }
}