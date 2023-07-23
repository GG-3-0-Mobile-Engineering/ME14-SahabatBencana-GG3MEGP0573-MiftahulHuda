package com.miftahulhudaf.sahabatbencana.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftahulhudaf.sahabatbencana.data.response.Disaster
import com.miftahulhudaf.sahabatbencana.databinding.AdapterDisasterBinding

class DisasterAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var disasterList = mutableListOf<Disaster>()

    fun setDisaster(disasters: List<Disaster>) {
        this.disasterList = disasters.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterDisasterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var disaster = disasterList[position]
        holder.binding.title.setText(disaster.properties?.title)
        Glide.with(holder.itemView.context).load(disaster.properties?.imageUrl).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return disasterList.size
    }
}

class MainViewHolder(val binding: AdapterDisasterBinding) : RecyclerView.ViewHolder(binding.root) {

}