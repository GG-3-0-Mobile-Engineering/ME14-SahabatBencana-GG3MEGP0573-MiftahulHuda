package com.miftahulhudaf.sahabatbencana.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miftahulhudaf.sahabatbencana.data.model.DisasterType
import com.miftahulhudaf.sahabatbencana.databinding.ListCategoryBinding

class DisasterTypeAdapter(private val onClickListener: OnDisasterTypeClickListener) :
    ListAdapter<DisasterType, DisasterTypeAdapter.ViewHolder>(DisasterTypeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ListCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(disasterType: DisasterType) {
            binding.tvCategoryName.text = disasterType.id
            binding.root.setOnClickListener {
                onClickListener.onDisasterTypeClick(binding.root, disasterType)
            }
        }
    }

    private class DisasterTypeDiffCallback : DiffUtil.ItemCallback<DisasterType>() {
        override fun areItemsTheSame(oldItem: DisasterType, newItem: DisasterType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DisasterType, newItem: DisasterType): Boolean {
            return oldItem.id == newItem.id && oldItem.en == newItem.en
        }
    }

    interface OnDisasterTypeClickListener {
        fun onDisasterTypeClick(view: View, disasterType: DisasterType)
    }
}