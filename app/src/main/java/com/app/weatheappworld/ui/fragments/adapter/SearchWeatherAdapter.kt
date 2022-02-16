package com.app.weatheappworld.ui.fragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.weatheappworld.databinding.FavRowBinding
import com.app.weatheappworld.remote.SearchWeather

class SearchWeatherAdapter(
    private val context: Context,
    itemClick: ItemClickList,
    var itemlist: ArrayList<SearchWeather>
) : RecyclerView.Adapter<SearchWeatherAdapter.ViewHolder>() {
    val itemClickSearch = itemClick

    inner class ViewHolder(private val binding: FavRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchWeather) {
            binding.city.text = item.name

            binding.tvtemp.text = item.main.temp.toString()

            binding.layoutClick.setOnClickListener {
                itemClickSearch.location(item.coord.lon, item.coord.lat)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        FavRowBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position])
    }

    override fun getItemCount() = itemlist.size


    interface ItemClickList {
        fun location(lon: Double, lat: Double)
    }

}