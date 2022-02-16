package com.app.weatheappworld

import com.app.weatheappworld.remote.ResponseWeather
import com.app.weatheappworld.remote.SearchWeather
import retrofit2.Response

interface RepositoryWeather {

    suspend fun getResponse(
        lat: Double,
        lon: Double,
        appid: String,
        units: String,
        lang: String,
        exclude: String
    ): Response<ResponseWeather>

    suspend fun getSearchWeather(
        appid: String,
        unit: String,
        lang: String,
        q: String
    ): Response<SearchWeather>


//    suspend fun insertOrUpdateWeather(entityWeather: EntityWeather)
//    suspend fun deleteWeather(entityWeather: EntityWeather)
//    suspend fun updateWeather(entityWeather: EntityWeather)
//    suspend fun getWeather(): List<EntityWeather>
//    //suspend fun searchWeather(searchQuery: String): List<EntityWeather>

}