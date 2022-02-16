package com.app.weatheappworld.utils

import android.app.Application

class Repositoryy (application:Application){

//    private val remoteDataSource = RemoteData
//    private val localDataSource = LocalDataSource.getInstance(application)
////    private val localAlarmDatabase = AlertDataSource.getInstance(application)
//
////    private val lat = application.getSharedPreferences(Constants.SHARED_PREF_CURRENT_LOCATION, Context.MODE_PRIVATE).getString(Constants.CURRENT_LATITUDE,"null").toString()
////    private val long = application.getSharedPreferences(Constants.SHARED_PREF_CURRENT_LOCATION, Context.MODE_PRIVATE).getString(Constants.CURRENT_LONGITUDE,"null").toString()
////    private val oldLat = application.getSharedPreferences(Constants.SHARED_PREF_CURRENT_LOCATION, Context.MODE_PRIVATE).getString(Constants.OLD_LATITUDE,"null").toString()
////    private val oldLong = application.getSharedPreferences(Constants.SHARED_PREF_CURRENT_LOCATION, Context.MODE_PRIVATE).getString(Constants.OLD_LONGITUDE,"null").toString()
////    private val language = application.getSharedPreferences(Constants.SHARED_PREF_SETTINGS, Context.MODE_PRIVATE).getString(Constants.LANGUAGE,"en").toString()
////    private val units = application.getSharedPreferences(Constants.SHARED_PREF_SETTINGS, Context.MODE_PRIVATE).getString(Constants.UNITS,"metric").toString()
//
//
//
//    fun loadAllData(): LiveData<List<ResponseWeather>> {
//        val exceptionHandlerException = CoroutineExceptionHandler { _, t:Throwable ->
//
//        }
//        CoroutineScope(Dispatchers.IO+exceptionHandlerException).launch {
//
//                val response = remoteDataSource.getWeather().getResponse(0.13, 0.13, "minutly", "metric", "en", Constants.KEY)
//                if (response.isSuccessful) {
//                    localDataSource.insertDefault(response.body())
////                    localDataSource.deleteDefault(oldLat,oldLong)
//
//                }
//
//        }
//        return localDataSource.getAllData()
//    }
//
//    fun refreshCurrentLocation(latitude:Double=0.13,longitude:Double=0.13){
//        runBlocking(Dispatchers.IO) {
//            launch {
//                try{
//
//                        val response = remoteDataSource.getWeather().getResponse(0.13, 0.13, "minutly", "metric", "en", Constants.KEY)
//                        if (response.isSuccessful) {
//                            localDataSource.insertDefault(response.body())
////                            localDataSource.deleteDefault(oldLat,oldLong)
////                            Log.i(Constants.LOG_TAG, "success22")
//                        }
//
//                }catch(e:Exception){
////                    Log.i(Constants.LOG_TAG,e.message.toString())
//                }
//            }
//        }
//    }

//    fun fetchFavouriteList(latitude: String, longitude: String): LiveData<List<ResponseWeather>> {
//        val exceptionHandlerException = CoroutineExceptionHandler { _, t:Throwable ->
//            Log.i(Constants.LOG_TAG,t.message.toString()) }
//        CoroutineScope(Dispatchers.IO+exceptionHandlerException).launch {
//            if(latitude!="0" && longitude!="0"){
////                val response = remoteDataSource.getWeatherService().getAllData(latitude, longitude, Constants.EXCLUDE_MINUTELY, units, language, Constants.WEATHER_API_KEY)
//                if (response.isSuccessful) {
//                    localDataSource.insertDefault(response.body())
//                    Log.i(Constants.LOG_TAG, "success fav")
//                }
//            }
//        }
//        Log.i(Constants.LOG_TAG, "outhere fav")
//        return localDataSource.getFavList(lat,long)
//    }
//
//    fun deleteFromFavourite(lat: String, lon: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            localDataSource.deleteDefault(lat,lon)
//        }
//    }
//
//    fun insertAlert(alert:AlertModel) {
//        CoroutineScope(Dispatchers.IO).launch {
//            localAlarmDatabase.insertAlarm(alert)
//        }
//    }
//
//    fun getAllAlerts(): LiveData<List<AlertModel>> {
//        return localAlarmDatabase.getAllAlarms()
//    }
//
//
//
//    fun deleteAlarmById(id:String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            localAlarmDatabase.deleteAlarmById(id)
//        }
//    }
//
//    fun refreshFavData(lat:String,long:String){
//        CoroutineScope(Dispatchers.IO).launch {
//            try{
//                val response = remoteDataSource.getWeatherService().getAllData(lat, long, Constants.EXCLUDE_MINUTELY, units, language, Constants.WEATHER_API_KEY)
//                if (response.isSuccessful) {
//                    localDataSource.insertDefault(response.body())
//                }
//            }catch(e:Exception){
//                Log.i(Constants.LOG_TAG,e.message.toString())
//            }
//        }
//    }
//
//    fun getUnrefreshedData():List<WeatherResponse>?{
//        var unrefreshedList:List<WeatherResponse>? = null
//        runBlocking(Dispatchers.IO){
//            launch {
//                unrefreshedList = localDataSource.getFavDataForRefresh()
//            }
//        }
//        return unrefreshedList
//    }


}