package com.miftahulhudaf.sahabatbencana.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftahulhudaf.sahabatbencana.data.model.Disaster
import com.miftahulhudaf.sahabatbencana.databinding.DisasterItemBinding

class DisasterAdapter : RecyclerView.Adapter<DisasterAdapter.MainViewHolder>() {

    var disasterList = mutableListOf<Disaster>()

    inner class MainViewHolder(private val binding: DisasterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(disaster: Disaster) {
            with(binding) {
                title.text = disaster.properties?.title
                Glide.with(itemView.context).load(disaster.properties?.imageUrl).into(binding.imageview)
            }
        }
    }

    fun setDisaster(disasters: List<Disaster>) {
        this.disasterList = disasters.toMutableList()
        Log.d("DisasterAdapter", this.disasterList.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DisasterItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var disaster = disasterList[position]
        holder.bind(disaster)
    }

    override fun getItemCount(): Int {
        return disasterList.size
    }
}
