package com.app.weatheappworld.utils

class ResourceWeather <out T>(val status: Status, val message: String?, val data: T?) {


    enum class Status {

        LOADING,
        SUCCESS,
        ERROR
    }


    companion object {

        fun <T> loading(): ResourceWeather<T> {
            return ResourceWeather(Status.LOADING, null, null)
        }

        fun <T> success(data: T): ResourceWeather<T> {

            return ResourceWeather(Status.SUCCESS, null, data)
        }

        fun <T> error(message: String): ResourceWeather<T> {

            return ResourceWeather(Status.ERROR, message, null)
        }
    }
}
