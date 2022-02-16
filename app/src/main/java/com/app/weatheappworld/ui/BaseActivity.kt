package com.app.weatheappworld.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import java.util.*

open class BaseActivity : AppCompatActivity() {

    open fun onViewCreated(view: View, savedInstanceState: Bundle?) {}

    fun getIcon(icon: String): String {
        return "http://openweathermap.org/img/w/${icon}.png"
    }
    companion object {
        public var dLocale: Locale? = null
    }

    init {
        updateConfig(this)
    }

    fun updateConfig(wrapper: ContextThemeWrapper) {
        if(dLocale==Locale("") ) // Do nothing if dLocale is null
            return

//        Locale.setDefault(dLocale)
        val configuration = Configuration()
        configuration.setLocale(dLocale)
        wrapper.applyOverrideConfiguration(configuration)
    }
    fun saveLang(lang: String) {
        val pref = applicationContext.getSharedPreferences(
            Constants.LANG,
            MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putString(Constants.LANG, lang)
        editor.commit()
    }

    fun getLang(): String? {
        val pref = applicationContext.getSharedPreferences(
            Constants.LANG,
            MODE_PRIVATE
        )
        return pref.getString(Constants.LANG, "").toString()
    }

    fun saveWind(wind: String) {
        val pref = applicationContext.getSharedPreferences(
            Constants.WIND,
            MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putString(Constants.WIND, wind)
        editor.commit()
    }


    fun getWind(): String? {
        val pref = applicationContext.getSharedPreferences(
            Constants.WIND,
            MODE_PRIVATE
        )
        return pref.getString(Constants.WIND, "").toString()
    }
    fun saveLat(lat: String?) {
        val pref = applicationContext.getSharedPreferences(
            Constants.LAT,
            MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putString(Constants.LAT, lat)
        editor.commit()
    }

    fun getLat(): String? {
        val pref = applicationContext.getSharedPreferences(
            Constants.LAT,
            MODE_PRIVATE
        )
        return pref.getString(Constants.LAT, "").toString()
    }
    fun saveLon(lon: String) {
        val pref = applicationContext.getSharedPreferences(
            Constants.LON,
            MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putString(Constants.LON, lon)
        editor.commit()
    }

    fun getLon(): String? {
        val pref = applicationContext.getSharedPreferences(
            Constants.LON,
            MODE_PRIVATE
        )
        return pref.getString(Constants.LON, "").toString()
    }

    fun saveUnits(unit: String) {
        val pref = applicationContext.getSharedPreferences(
            Constants.UNITS,
            MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putString(Constants.UNITS, unit)
        editor.commit()
    }

    open fun getUnits(): String? {
        val pref = applicationContext.getSharedPreferences(
            Constants.UNITS,
            MODE_PRIVATE
        )
        return pref.getString(Constants.UNITS, "").toString()
    }

    fun savePrefsData(t: Boolean) {
        val pref = applicationContext.getSharedPreferences(
            Constants.WEATHER_PREFERENCES,
            MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putBoolean(Constants.WEATHER_PREFERENCES_SAVE, t)
        editor.commit()
    }

    fun checkPrefData(): Boolean {
        val pref = applicationContext.getSharedPreferences(
            Constants.WEATHER_PREFERENCES,
            MODE_PRIVATE
        )
        return pref.getBoolean(Constants.WEATHER_PREFERENCES_SAVE, false)
    }

    fun saveSettingHome(t: Boolean) {
        val pref = applicationContext.getSharedPreferences(
            Constants.Weather,
            MODE_PRIVATE
        )
        val editor = pref.edit()
        editor.putBoolean(Constants.WEATHERHOME, t)
        editor.commit()
    }

    fun checkSettingHome(): Boolean {
        val pref = applicationContext.getSharedPreferences(
            Constants.Weather,
            MODE_PRIVATE
        )
        return pref.getBoolean(Constants.WEATHERHOME, false)
    }


}
