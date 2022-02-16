package com.app.weatheappworld.model.dataconverter

import androidx.room.TypeConverter

import com.app.weatheappworld.remote.Hourly
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HourlyItemTypeConverter {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromHourlyItemList(value: MutableList<Hourly>): String {
            val gson = Gson()
            val type = object : TypeToken<MutableList<Hourly>>() {}.type
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toHourlyItemList(value: String): MutableList<Hourly> {
            val gson = Gson()
            val type = object : TypeToken<MutableList<Hourly>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}

