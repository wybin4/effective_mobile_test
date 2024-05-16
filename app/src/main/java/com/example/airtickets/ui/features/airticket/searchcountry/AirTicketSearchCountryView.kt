package com.example.airtickets.ui.features.airticket.searchcountry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.airtickets.R
import com.example.airtickets.databinding.AirTicketSearchCountryBinding
import com.example.airtickets.databinding.TicketOfferBinding
import com.example.airtickets.ui.features.airticket.models.TicketOfferUiModel
import com.example.airtickets.ui.models.state.ListState
import com.example.airtickets.ui.utils.CustomDatePicker
import com.example.airtickets.ui.utils.CustomListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date

class AirTicketSearchCountryView : Fragment() {
    private var _binding: AirTicketSearchCountryBinding? = null
    private val binding get() = _binding!!
    private val airTicketViewModel: AirTicketSearchCountryViewModel by viewModel<AirTicketSearchCountryViewModel>()

    private lateinit var ticketOfferListAdapter: CustomListAdapter<TicketOfferUiModel, TicketOfferBinding>
    private lateinit var sourceDatePicker: CustomDatePicker
    private lateinit var destinationDatePicker: CustomDatePicker

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AirTicketSearchCountryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewModel = airTicketViewModel

        airTicketViewModel.parcelable = requireArguments().getParcelable("sourceDest")!!

        setupDatePickers()
        setupRecyclerView()
        binding.apply {
            clearDestination.setOnClickListener { goBack() }
            back.setOnClickListener { goBack() }
            swap.setOnClickListener {
                val source = airTicketViewModel.parcelable.source
                val dest = airTicketViewModel.parcelable.dest
                airTicketViewModel.parcelable.source = dest
                airTicketViewModel.parcelable.dest = source
                binding.source.text = dest
                binding.destination.text = source
            }
            viewAllTickets.setOnClickListener {
                val bundle = airTicketViewModel.createParcel().toBundle()
                findNavController().navigate(R.id.action_airTicketSearchCountry_to_airTicketAll, bundle)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        airTicketViewModel.ticketOfferList.observe(viewLifecycleOwner) {
            ticketOfferListAdapter.submitList(it)
        }
        airTicketViewModel.ticketOfferListState.observe(viewLifecycleOwner) { state ->
            updateViewState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        airTicketViewModel.fetchTicketOfferList()
    }

    private fun setDate(ddmmyy: TextView, dayOfWeek: TextView, date: Date) {
        ddmmyy.text = airTicketViewModel.formatDate(date) + ", "
        dayOfWeek.text = airTicketViewModel.formatDayOfWeek(date)
    }

    private fun setupDatePickers() {
        val currentDate = Date()
        setDate(binding.sourceDateDdmmyy, binding.sourceDayOfWeek, currentDate)
        sourceDatePicker = CustomDatePicker(requireActivity()) { date ->
            airTicketViewModel.sourceDate = date
            setDate(binding.sourceDateDdmmyy, binding.sourceDayOfWeek, date)
        }
        destinationDatePicker = CustomDatePicker(requireActivity()) { date ->
            airTicketViewModel.destinationDate = date
            binding.destinationDateDefault.visibility = View.GONE
            binding.destinationDateDdmmyy.visibility = View.VISIBLE
            binding.destinationDayOfWeek.visibility = View.VISIBLE
            setDate(binding.destinationDateDdmmyy, binding.destinationDayOfWeek, date)
        }
        binding.sourceDate.setOnClickListener {
            sourceDatePicker.show()
        }
        binding.destinationDate.setOnClickListener {
            destinationDatePicker.show()
        }
    }

    private fun goBack() {
        findNavController().navigate(R.id.action_airTicketSearchCountry_to_airTicketMain)
    }

    private fun setupRecyclerView() {
        ticketOfferListAdapter = CustomListAdapter(
            itemBindingInflater = { inflater, parent, attachToParent ->
                TicketOfferBinding.inflate(inflater, parent, attachToParent)
            },
            setBinding = { binding, item ->
                binding.ticketOffer = item
            }, onItemClick = null
        )
        binding.recycler.adapter = ticketOfferListAdapter
    }

    private fun updateViewState(state: ListState) {
        binding.h2Wrapper.visibility = state.successVisibility
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}