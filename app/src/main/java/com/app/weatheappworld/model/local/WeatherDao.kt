package com.app.weatheappworld.model.local

import androidx.room.*

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrUpdateWeather(entityWeather:EntityWeather)

    @Delete
    suspend fun deleteWeather(entityWeather:EntityWeather)

    @Update
    suspend fun updateWeather(entityWeather:EntityWeather)

    @Query("select * from Weather_table")
    suspend fun getWeather(): List<EntityWeather>

//    @Query("select * from weather_table WHERE  nameLocSearch LIKE :searchQuery ")
//    suspend fun searchWeather(searchQuery: String): List<EntityWeather>

}