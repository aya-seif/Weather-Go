package com.app.weatheappworld.model.dataconverter

import androidx.room.TypeConverter
import com.app.weatheappworld.remote.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherItemTypeConverter {
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromWeatherItemList(value: MutableList<Weather>): String {
            val gson = Gson()
            val type = object : TypeToken<MutableList<Weather>>() {}.type
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toWeatherItemList(value: String): MutableList<Weather> {
            val gson = Gson()
            val type = object : TypeToken<MutableList<Weather>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}