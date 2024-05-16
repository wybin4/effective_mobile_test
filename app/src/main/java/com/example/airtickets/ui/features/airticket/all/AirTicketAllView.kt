package com.example.airtickets.ui.features.airticket.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.airtickets.databinding.AirTicketAllBinding
import com.example.airtickets.databinding.TicketBinding
import com.example.airtickets.ui.features.airticket.models.TicketUiModel
import com.example.airtickets.ui.models.state.ListState
import com.example.airtickets.ui.utils.CustomListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AirTicketAllView : Fragment() {
    private var _binding: AirTicketAllBinding? = null
    private val binding get() = _binding!!
    private val airTicketViewModel: AirTicketAllViewModel by viewModel<AirTicketAllViewModel>()

    private lateinit var ticketListAdapter: CustomListAdapter<TicketUiModel, TicketBinding>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AirTicketAllBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewModel = airTicketViewModel

        airTicketViewModel.parcelable = requireArguments().getParcelable("sourceDest")!!

        setupRecyclerView()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        airTicketViewModel.ticketList.observe(viewLifecycleOwner) {
            ticketListAdapter.submitList(it)
        }
        airTicketViewModel.ticketListState.observe(viewLifecycleOwner) { state ->
            updateViewState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        airTicketViewModel.fetchTicketOfferList()
    }

    private fun setupRecyclerView() {
        ticketListAdapter = CustomListAdapter(
            itemBindingInflater = { inflater, parent, attachToParent ->
                TicketBinding.inflate(inflater, parent, attachToParent)
            },
            setBinding = { binding, item ->
                binding.ticket = item
            }, onItemClick = null
        )
        val headerAdapter = AirTicketAllHeaderAdapter { headerBinding ->
            headerBinding.sourceAndPassCount.text = airTicketViewModel.parcelable.formatSourceAndPassCount()
            headerBinding.sourceDest.text = airTicketViewModel.parcelable.getSourceDashDest()
            headerBinding.back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        val concatAdapter = ConcatAdapter(headerAdapter, ticketListAdapter)
        binding.recycler.adapter = concatAdapter
    }

    private fun updateViewState(state: ListState) {
        binding.menu.visibility = state.successVisibility
        binding.recycler.visibility = state.successVisibility
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}