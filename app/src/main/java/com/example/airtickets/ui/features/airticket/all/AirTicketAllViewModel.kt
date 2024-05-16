package com.example.airtickets.ui.features.airticket.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airtickets.ui.features.airticket.AirTicketMapper
import com.example.airtickets.ui.features.airticket.models.AirTicketMainToSearchCountryParcelableModel
import com.example.airtickets.ui.features.airticket.models.AirTicketSearchCountryToAllParcelableModel
import com.example.airtickets.ui.features.airticket.models.TicketUiModel
import com.example.airtickets.ui.models.state.ListState
import com.example.airtickets.ui.models.networkresult.asNetworkResult
import com.example.airtickets.ui.models.state.asListState
import com.example.data.features.airticket.AirTicketRepository
import kotlinx.coroutines.launch

class AirTicketAllViewModel(
    private val repository: AirTicketRepository,
    private val mapper: AirTicketMapper
) : ViewModel() {
    private val _ticketList = MutableLiveData<List<TicketUiModel>>()
    val ticketList: LiveData<List<TicketUiModel>> = _ticketList

    private val _ticketListState = MutableLiveData<ListState>(ListState.Loading)
    val ticketListState: LiveData<ListState> = _ticketListState

    lateinit var parcelable: AirTicketSearchCountryToAllParcelableModel

    fun fetchTicketOfferList() {
        viewModelScope.launch {
            repository.listTicket()
                .asNetworkResult()
                .collect {
                    it.asListState(_ticketListState) { data ->
                        _ticketList.value = mapper.ticketListToUi(data)
                    }
                }
        }
    }
}