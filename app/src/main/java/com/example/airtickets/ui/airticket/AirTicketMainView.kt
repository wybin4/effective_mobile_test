package com.example.airtickets.ui.airticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.airtickets.databinding.AirTicketMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AirTicketMainView : Fragment() {
    private var _binding: AirTicketMainBinding? = null
    private val binding get() = _binding!!
    private val airTicketMainViewModel: AirTicketMainViewModel by viewModel<AirTicketMainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AirTicketMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        airTicketMainViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}