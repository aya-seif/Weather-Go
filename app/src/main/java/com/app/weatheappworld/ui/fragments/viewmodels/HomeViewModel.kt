package com.app.weatheappworld.ui.fragments.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.weatheappworld.RepositoryImp
import com.app.weatheappworld.model.local.EntityWeather
import com.app.weatheappworld.model.local.WeatherDatabase
import com.app.weatheappworld.remote.ResponseWeather
import com.app.weatheappworld.remote.SearchWeather
import com.app.weatheappworld.retrofit.ApiService
import com.app.weatheappworld.retrofit.RetrofitBuilder
import com.app.weatheappworld.utils.ResourceWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: RepositoryImp

    init {
        //val db = WeatherDatabase.getInstance(application)
        val api = RetrofitBuilder.retrofit.create(ApiService::class.java)
        repo = RepositoryImp(api)
    }

    private val mutableResponse = MutableLiveData<ResourceWeather<ResponseWeather>>()
    val liveDataResponse: LiveData<ResourceWeather<ResponseWeather>> get() = mutableResponse

    private val mutableSearch = MutableLiveData<ResourceWeather<SearchWeather>>()
    val liveDataSearch: LiveData<ResourceWeather<SearchWeather>> get() = mutableSearch



//    private val mutableSearchDatabase = MutableLiveData<List<EntityWeather>>()
//    val liveDataSearchDatabase: LiveData<List<EntityWeather>> get() = mutableSearchDatabase
//
//    private val mutableHomeDatabase = MutableLiveData<List<EntityWeather>>()
//    val liveDataHomeDatabase: LiveData<List<EntityWeather>> get() = mutableHomeDatabase
//
//    private val mutableHourlyDatabase = MutableLiveData<EntityWeather>()
//    val liveDataHourlyDatabase: LiveData<EntityWeather> get() = mutableHourlyDatabase
//
//    private val mutableDailyDatabase = MutableLiveData<EntityWeather>()
//    val liveDataDailyDatabase: LiveData<EntityWeather> get() = mutableDailyDatabase


    fun getResponse(
        lat: Double,
        lon: Double,
        appid: String,
        unit: String,
        lang: String,
        exclude: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        mutableResponse.postValue(ResourceWeather.loading())
        val result = repo.getResponse(lat, lon, appid,unit,lang,exclude)
        if (result.isSuccessful) {
            if (result.body() != null) {
                mutableResponse.postValue(ResourceWeather.success(result.body()!!))
            }
        }
    }


    fun getSearch(
        appid: String,
        unit: String,
        lang: String,
        q: String,
    ) = viewModelScope.launch(Dispatchers.IO) {
        mutableSearch.postValue(ResourceWeather.loading())
        val result = repo.getSearchWeather( appid,unit,lang,q)
        if (result.isSuccessful) {
            if (result.body() != null) {
                mutableSearch.postValue(ResourceWeather.success(result.body()!!))
            }
        }
    }

//    fun getHomeDatabase() = viewModelScope.launch(Dispatchers.IO) {
//            mutableHomeDatabase.postValue(repo.getWeather())
//    }
//
////    fun getSearchDatabase(searchQuery: String) = viewModelScope.launch(Dispatchers.IO) {
////        mutableSearchDatabase.postValue(repo.searchWeather(searchQuery))
////    }
//
//    fun getHourlyDatabase(entityWeather: EntityWeather) = viewModelScope.launch(Dispatchers.IO) {
//        repo.insertOrUpdateWeather(entityWeather)
//    }
//
//    fun getDailyDatabase(entityWeather: EntityWeather) = viewModelScope.launch(Dispatchers.IO) {
//        repo.insertOrUpdateWeather(entityWeather)
//    }

}