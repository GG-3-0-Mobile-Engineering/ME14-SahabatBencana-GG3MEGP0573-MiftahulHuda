package com.miftahulhudaf.sahabatbencana.ui.main.adapter

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.miftahulhudaf.sahabatbencana.data.response.archive.DisasterProperty
import com.miftahulhudaf.sahabatbencana.data.utils.LocalData
import com.miftahulhudaf.sahabatbencana.databinding.DisasterItemBinding
import com.miftahulhudaf.sahabatbencana.utils.loadImageFromUrl


class DisasterAdapter : RecyclerView.Adapter<DisasterAdapter.MainViewHolder>() {

    var disasterList = mutableListOf<DisasterProperty>()

    inner class MainViewHolder(private val binding: DisasterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(disaster: DisasterProperty) {
            with(binding) {

                if(disaster.title.isNullOrEmpty()) {
                    val disasterTypes = LocalData.getDisasterTypes()
                    val disasterDesc = disasterTypes.filter { type -> type.en == disaster.disasterType }
                    title.text = "Bencana Alam ${disasterDesc[0].id}"
                } else {
                    title.text = disaster.title
                }

                if(disaster.text.isNullOrEmpty()) {
                    desc.text = "Deskripsi Bencana"
                } else {
                    desc.text = disaster?.text?.take(100)
                }


                if(disaster.imageUrl.isNullOrEmpty()) {
                    imageview.loadImageFromUrl("")
                } else {
                    imageview.loadImageFromUrl(disaster.imageUrl)
                }
            }
        }
    }

    fun setDisaster(disasters: List<DisasterProperty>) {
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
