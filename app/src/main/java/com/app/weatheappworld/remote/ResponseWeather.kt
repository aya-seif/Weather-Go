package com.app.weatheappworld.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponseWeather(

    @SerializedName("current")
    @Expose
    val current: Current,
    @SerializedName("daily")
    @Expose
    val daily: List<Daily>,
    @SerializedName("hourly")
    @Expose
    val hourly: List<Hourly>,
    @SerializedName("lat")
    @Expose
    val lat: Double,
    @SerializedName("lon")
    @Expose
    val lon: Double,
    @SerializedName("minutely")
    @Expose
    val minutely: List<Minutely>,
    @SerializedName("timezone")
    @Expose
    val timezone: String,
    @SerializedName("timezone_offset")
    @Expose
    val timezoneOffset: Any
)

data class Current(
    @SerializedName("clouds")
    @Expose
    val clouds: Any,
    @SerializedName("dew_point")
    @Expose
    val dewPoint: Double,
    @SerializedName("dt")
    @Expose
    val dt: Int,
    @SerializedName("feels_like")
    @Expose
    val feelsLike: Double,
    @SerializedName("humidity")
    @Expose
    val humidity: Any,
    @SerializedName("pressure")
    @Expose
    val pressure: Any,
    @SerializedName("sunrise")
    @Expose
    val sunrise: Any,
    @SerializedName("sunset")
    @Expose
    val sunset: Any,
    @SerializedName("temp")
    @Expose
    val temp: Double,
    @SerializedName("uvi")
    @Expose
    val uvi: Any,
    @SerializedName("visibility")
    @Expose
    val visibility: Any,
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    @Expose
    val windDeg: Any,
    @SerializedName("wind_gust")
    @Expose
    val windGust: Double,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Double
)

data class Daily(
    @SerializedName("clouds")
    @Expose
    val clouds: Any,
    @SerializedName("dew_point")
    @Expose
    val dewPoint: Double,
    @SerializedName("dt")
    @Expose
    val dt: Int,
    @SerializedName("feels_like")
    @Expose
    val feelsLike: FeelsLike,
    @SerializedName("humidity")
    @Expose
    val humidity: Any,
    @SerializedName("moon_phase")
    @Expose
    val moonPhase: Double,
    @SerializedName("moonrise")
    @Expose
    val moonrise: Any,
    @SerializedName("moonset")
    @Expose
    val moonset: Any,
    @SerializedName("pop")
    @Expose
    val pop: Double,
    @SerializedName("pressure")
    @Expose
    val pressure: Any,
    @SerializedName("snow")
    @Expose
    val snow: Double,
    @SerializedName("sunrise")
    @Expose
    val sunrise: Any,
    @SerializedName("sunset")
    @Expose
    val sunset: Any,
    @SerializedName("temp")
    @Expose
    val temp: Temp,
    @SerializedName("uvi")
    @Expose
    val uvi: Double,
    @SerializedName("weather")
    @Expose
    val weather: List<WeatherX>,
    @SerializedName("wind_deg")
    @Expose
    val windDeg: Any,
    @SerializedName("wind_gust")
    @Expose
    val windGust: Double,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Double
)

data class Hourly(
    @SerializedName("clouds")
    @Expose
    val clouds: Any,
    @SerializedName("dew_point")
    @Expose
    val dewPoint: Double,
    @SerializedName("dt")
    @Expose
    val dt: Int,
    @SerializedName("feels_like")
    @Expose
    val feelsLike: Double,
    @SerializedName("humidity")
    @Expose
    val humidity: Any,
    @SerializedName("pop")
    @Expose
    val pop: Double,
    @SerializedName("pressure")
    @Expose
    val pressure: Any,
    @SerializedName("snow")
    @Expose
    val snow: Snow,
    @SerializedName("temp")
    @Expose
    val temp: Double,
    @SerializedName("uvi")
    @Expose
    val uvi: Double,
    @SerializedName("visibility")
    @Expose
    val visibility: Any,
    @SerializedName("weather")
    @Expose
    val weather: List<WeatherXX>,
    @SerializedName("wind_deg")
    @Expose
    val windDeg: Any,
    @SerializedName("wind_gust")
    @Expose
    val windGust: Double,
    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Double
)

data class Minutely(
    @SerializedName("dt")
    @Expose
    val dt: Any,
    @SerializedName("precipitation")
    @Expose
    val precipitation: Any
)

data class Weather(
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("icon")
    @Expose
    val icon: String,
    @SerializedName("id")
    @Expose
    val id: Any,
    @SerializedName("main")
    @Expose
    val main: String
)

data class FeelsLike(
    @SerializedName("day")
    @Expose
    val day: Double,
    @SerializedName("eve")
    @Expose
    val eve: Double,
    @SerializedName("morn")
    @Expose
    val morn: Double,
    @SerializedName("night")
    @Expose
    val night: Double
)

data class Temp(
    @SerializedName("day")
    @Expose
    val day: Double,
    @SerializedName("eve")
    @Expose
    val eve: Double,
    @SerializedName("max")
    @Expose
    val max: Double,
    @SerializedName("min")
    @Expose
    val min: Double,
    @SerializedName("morn")
    @Expose
    val morn: Double,
    @SerializedName("night")
    @Expose
    val night: Double
)

data class WeatherX(
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("icon")
    @Expose
    val icon: String,
    @SerializedName("id")
    @Expose
    val id: Any,
    @SerializedName("main")
    @Expose
    val main: String
)

data class Snow(
    @SerializedName("1h")
    @Expose
    val h: Double
)

data class WeatherXX(
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("icon")
    @Expose
    val icon: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("main")
    @Expose
    val main: String
)
