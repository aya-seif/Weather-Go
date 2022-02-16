package com.app.weatheappworld.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.weatheappworld.R
import com.app.weatheappworld.databinding.DailyRowBinding
import com.app.weatheappworld.remote.Daily
import com.app.weatheappworld.ui.BaseActivity
import com.app.weatheappworld.ui.activity.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

class DailyAdapter(var dailyList: ArrayList<Daily>) :
    RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {


    fun updateDailyList(newDailyList: List<Daily>) {
        dailyList.clear()
        dailyList.addAll(newDailyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = DailyViewHolder(
        DailyRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return dailyList.size
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(dailyList[position])

    }

    class DailyViewHolder(private val binding: DailyRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val dt = binding.dt
        private val dailyDayIcon = binding.dayIcon
        private val dailyDayTemp = binding.daytemp
//        val activity:MainActivity= activity as MainActivity

        fun bind(daily: Daily) {
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat(" EE:dd:MM", Locale.getDefault())
            val currenTimeZone = (daily.dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
            dt.text = sdf.format(currenTimeZone)

//            when()
            dailyDayTemp.text = (daily.temp?.day)?.toInt().toString()+""
            val dayIcon = daily.weather?.get(0)?.icon
            val options = RequestOptions()
                .error(R.drawable.cloud)
            Glide.with(dailyDayIcon.context)
                .setDefaultRequestOptions(options)
                .load(dayIcon?.let { it2 -> getIcon(it2) })
                .into(dailyDayIcon)
            val nightIcon = daily.weather?.get(0)?.icon?.substring(0, 2) + "n"
        }
        fun getIcon(icon: String): String {
            return "http://openweathermap.org/img/w/${icon}.png"
        }
        fun getDt(dt: Int?): String {
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat("HH:mm a", Locale.getDefault())
            val currenTimeZone = (dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
            return sdf.format(currenTimeZone)
        }
    }
}