package com.example.airtickets.ui.features.airticket.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.airtickets.R
import com.example.airtickets.databinding.AirTicketMainBinding
import com.example.airtickets.databinding.AirTicketSearchBinding
import com.example.airtickets.databinding.OfferBinding
import com.example.airtickets.ui.features.airticket.models.OfferUiModel
import com.example.airtickets.ui.models.state.ListState
import com.example.airtickets.ui.utils.CustomListAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel


class AirTicketMainView : Fragment() {
    private var _bindingList: AirTicketMainBinding? = null
    private val bindingList get() = _bindingList!!
    private var _bindingSearch: AirTicketSearchBinding? = null
    private val bindingSearch get() = _bindingSearch!!
    private val airTicketViewModel: AirTicketMainViewModel by viewModel<AirTicketMainViewModel>()

    private lateinit var offerListAdapter: CustomListAdapter<OfferUiModel, OfferBinding>
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingList = AirTicketMainBinding.inflate(inflater, container, false)
        val root: View = bindingList.root

        setupRecyclerView()
        setupSharedPreferences()
        setupSearchSheet(inflater, container)
        setupDestinationPickers()

        bindingList.destination.setOnClickListener {
            val sourceText = airTicketViewModel.loadSource()
            if (sourceText != "") {
                bindingSearch.source.text = sourceText
                bottomSheetDialog.show()
            } else {
                bindingList.sourceError.visibility = View.VISIBLE
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        airTicketViewModel.offerList.observe(viewLifecycleOwner) {
            offerListAdapter.submitList(it)
        }
        airTicketViewModel.offerListState.observe(viewLifecycleOwner) { state ->
            updateViewState(state)
        }
    }

    override fun onResume() {
        super.onResume()
        airTicketViewModel.fetchOfferList()
    }

    private fun setupSharedPreferences() {
        bindingList.source.apply {
            setText(airTicketViewModel.loadSource())
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Не используем
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    bindingList.sourceError.visibility = View.GONE
                }

                override fun afterTextChanged(s: Editable?) {
                    airTicketViewModel.saveSource(s.toString())
                }
            })
        }
    }

    private fun setupDestinationPickers() {
        bindingSearch.apply {
            destination.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    fillDestination(textView.text.toString())
                    return@setOnEditorActionListener true
                }
                false
            }
            istanbul.setOnClickListener { fillDestination(R.string.istanbul) }
            sochi.setOnClickListener { fillDestination(R.string.sochi) }
            phuket.setOnClickListener { fillDestination(R.string.phuket) }
            anywhere.setOnClickListener { fillDestination(R.string.anywhere) }
            clearDestination.setOnClickListener { bindingSearch.destination.setText("") }

            difficultRoute.setOnClickListener {  toggleStub(true) }
            weekend.setOnClickListener { toggleStub(true)  }
            hotTickets.setOnClickListener { toggleStub(true) }
            backFromStub.setOnClickListener { toggleStub(false) }
        }
    }

    private fun toggleStub(isVisible: Boolean) {
        if (isVisible) {
            bindingSearch.stub.visibility = View.VISIBLE
            bindingSearch.popularDestinations.visibility = View.GONE
        } else {
            bindingSearch.stub.visibility = View.GONE
            bindingSearch.popularDestinations.visibility = View.VISIBLE
        }
    }

    private fun fillDestination(text: Any) {
        val destinationText = when (text) {
            is Int -> getString(text)
            is String -> text
            else -> ""
        }
        bindingSearch.destination.setText(destinationText)
        airTicketViewModel.destination = destinationText
        bottomSheetDialog.dismiss()
        val bundle = airTicketViewModel.createParcel().toBundle()
        findNavController().navigate(R.id.action_airTicketMain_to_airTicketSearchCountry, bundle)
    }

    private fun setupSearchSheet(inflater: LayoutInflater, container: ViewGroup?) {
        bottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.SheetDialog)
        _bindingSearch = AirTicketSearchBinding.inflate(inflater, container, false)
        bottomSheetDialog.setContentView(bindingSearch.root)

        bindingList.bottomSheetDragHandle.setOnClickListener {
            if (bottomSheetDialog.isShowing) {
                bottomSheetDialog.dismiss()
            } else {
                bottomSheetDialog.show()
            }
        }
    }

    private fun setupRecyclerView() {
        offerListAdapter = CustomListAdapter(
            itemBindingInflater = { inflater, parent, attachToParent ->
                OfferBinding.inflate(inflater, parent, attachToParent)
            },
            setBinding = { bindingList, item ->
                bindingList.offer = item
            }, onItemClick = null
        )
        bindingList.recycler.adapter = offerListAdapter
    }

    private fun updateViewState(state: ListState) {
        bindingList.apply {
            h2AirTickets.visibility = state.successVisibility
            recycler.visibility = state.successVisibility
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingList = null
        _bindingSearch = null
    }
}