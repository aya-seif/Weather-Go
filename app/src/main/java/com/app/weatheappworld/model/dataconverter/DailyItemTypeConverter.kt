package com.app.weatheappworld.model.dataconverter

import androidx.room.TypeConverter
import com.app.weatheappworld.remote.Daily
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DailyItemTypeConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromDailyItemList(value: MutableList<Daily>): String {
            val gson = Gson()
            val type = object : TypeToken<MutableList<Daily>>() {}.type
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toDailyItemList(value: String): MutableList<Daily> {
            val gson = Gson()
            val type = object : TypeToken<MutableList<Daily>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}