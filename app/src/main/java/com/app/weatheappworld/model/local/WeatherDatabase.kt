package com.app.weatheappworld.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


private const val DATABASE_NAME = "database_weather"

@Database(entities = [EntityWeather::class], version = 1, exportSchema = false)
abstract class WeatherDatabase :RoomDatabase(){
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var instance: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            return instance ?: synchronized(Any()) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(
                context.applicationContext, WeatherDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}