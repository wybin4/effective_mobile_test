package com.example.airtickets.ui.features.airticket.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airtickets.databinding.AirTicketAllHeaderBinding

class AirTicketAllHeaderAdapter(
    private val setBinding: (AirTicketAllHeaderBinding) -> Unit
) : RecyclerView.Adapter<AirTicketAllHeaderAdapter.HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AirTicketAllHeaderBinding.inflate(inflater, parent, false)
        return HeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class HeaderViewHolder(
        private val binding: AirTicketAllHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            setBinding(binding)
            binding.executePendingBindings()
        }
    }
}
