package com.example.airtickets.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airtickets.ui.models.AdaptiveInt

open class CustomListAdapter<T: AdaptiveInt, B : ViewDataBinding>(
    private val itemBindingInflater: (LayoutInflater, ViewGroup, Boolean) -> B,
    private val setBinding: (B, T) -> Unit,
    private val onItemClick: ((item: T) -> Unit)?
) : ListAdapter<T, CustomListAdapter<T, B>.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = itemBindingInflater(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    inner class ItemViewHolder(private val binding: B) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            setBinding(binding, item)
            binding.executePendingBindings()
        }
    }

    class DiffCallback<T: AdaptiveInt> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }
    }

}

