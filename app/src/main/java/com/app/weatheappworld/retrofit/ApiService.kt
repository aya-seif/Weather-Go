package com.app.weatheappworld.retrofit

import com.app.weatheappworld.remote.ResponseWeather
import com.app.weatheappworld.remote.SearchWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("onecall")
    suspend fun getResponse(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("exclude") exclude: String
    ): Response<ResponseWeather>

    @GET("weather")
    suspend fun getSearchData(
        @Query("appid") appid: String,
        @Query("unit") unit: String,
        @Query("lang") lang: String,
        @Query("q") q: String
    ): Response<SearchWeather>
}