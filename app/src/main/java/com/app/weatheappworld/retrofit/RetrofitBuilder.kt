package com.app.weatheappworld.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {


    companion object {

        private const val BaseUrl = "https://api.openweathermap.org/data/2.5/"


        val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

                val client = OkHttpClient.Builder()
                     .addInterceptor(logging)
                     .connectTimeout(45, TimeUnit.SECONDS)
                     .readTimeout(45, TimeUnit.SECONDS)
                     .writeTimeout(45, TimeUnit.SECONDS)
                     .build()

            Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }



}
