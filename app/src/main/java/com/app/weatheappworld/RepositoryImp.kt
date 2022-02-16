package com.app.weatheappworld

import com.app.weatheappworld.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class RepositoryImp(private val api: ApiService) :
    RepositoryWeather {

    override suspend fun getResponse(
        lat: Double,
        lon: Double,
        appid: String,
        units: String,
        lang: String,
        exclude: String
    ) = withContext(Dispatchers.IO) {
        api.getResponse(lat, lon, appid, units, lang, exclude)
    }


    override suspend fun getSearchWeather(appid: String, unit: String, lang: String, q: String) =
        withContext(Dispatchers.IO) {
            api.getSearchData(appid, unit, lang, q)
        }

//    override suspend fun insertOrUpdateWeather(entityWeather: EntityWeather) {
//        withContext(Dispatchers.IO) {
//            db.weatherDao().insertOrUpdateWeather(entityWeather)
//        }
//    }
//
//    override suspend fun deleteWeather(entityWeather: EntityWeather) {
//        withContext(Dispatchers.IO) {
//            db.weatherDao().deleteWeather(entityWeather)
//        }
//    }
//
//    override suspend fun updateWeather(entityWeather: EntityWeather) {
//        withContext(Dispatchers.IO) {
//            db.weatherDao().updateWeather(entityWeather)
//        }
//    }
//
//    override suspend fun getWeather() = withContext(Dispatchers.IO)
//    {
//        db.weatherDao().getWeather()
//    }

//    override suspend fun searchWeather(searchQuery: String) = withContext(Dispatchers.IO) {
//        db.weatherDao().searchWeather(searchQuery)
//    }

}