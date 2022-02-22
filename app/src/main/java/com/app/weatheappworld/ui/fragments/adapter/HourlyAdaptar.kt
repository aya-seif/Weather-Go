package com.app.weatheappworld.ui.fragments.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.app.weatheappworld.R
import com.app.weatheappworld.databinding.ItemHourlyBinding
import com.app.weatheappworld.remote.Hourly
import com.app.weatheappworld.ui.activity.MainActivity
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class HourlyAdapter(var hoursList: ArrayList<Hourly>) : RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>(){

    fun updateHoursList(newCountries: List<Hourly>, activity: MainActivity) {
        hoursList.clear()
        hoursList.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = HourlyViewHolder(
        ItemHourlyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )


    override fun getItemCount(): Int {
        return hoursList.size
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(hoursList[position])
    }

    class HourlyViewHolder(private val binding: ItemHourlyBinding) : RecyclerView.ViewHolder(binding.root) {
        private val hourlyTime =binding.timeTextView
        private val hourlyIcon=binding.imageView
        private val hourlyTemp=binding.tempTextView



        fun bind(hourly:Hourly){
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat("HH:mm a", Locale.getDefault())
            val currenTimeZone = (hourly.dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
            hourlyTime.text= sdf.format(currenTimeZone)

            hourlyTemp.text=(hourly.temp)?.toInt().toString()
            val icon=hourly.weather?.get(0)?.icon

            try {
                Picasso.get()
                    .load(getIcon(icon))
                    .placeholder(R.drawable.cloud)
                    .error(R.drawable.cloud)
                    .into(hourlyIcon)

                Log.d("TAG","${getIcon(icon)}")
                Glide.with(itemView.context)
                    .load(Uri.parse(getIcon(icon)))
                    .centerCrop()
                    .into(hourlyIcon)
            } catch (e: IOException){
                e.printStackTrace()
            }


        }
        fun getIcon(icon: String): String {
            return "http://openweathermap.org/img/w/${icon}.png"
        }

    }

}