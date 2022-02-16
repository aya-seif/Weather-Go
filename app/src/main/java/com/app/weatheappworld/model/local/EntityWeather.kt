package com.app.weatheappworld.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Weather_table")
class EntityWeather (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    //Home
    @ColumnInfo(name = "nameLocHome") val nameLocHome: String,
    @ColumnInfo(name = "tempHome") val tempHome: String,
    @ColumnInfo(name = "dateHome") val dateHome: String,
    @ColumnInfo(name = "clouds") val clouds: String,
    @ColumnInfo(name = "descrption") val description: String,
    @ColumnInfo(name = "minTemp") val minTemp: String,
    @ColumnInfo(name = "maxTemp") val maxTemp: String,
    @ColumnInfo(name = "wind") val wind: String,
    @ColumnInfo(name = "humidity") val humidity: String,
    @ColumnInfo(name = "pressure") val pressure: String,

    //RecHourly
    @ColumnInfo(name = "timeHourly") val timeHourly: String,
    @ColumnInfo(name = "iconHourly") val iconHourly: String,
    @ColumnInfo(name = "tempHourly") val tempHourly: String,

    //RecDaily
    @ColumnInfo(name = "timeDaily") val timeDaily: String,
    @ColumnInfo(name = "iconDaily") val iconDaily: String,
    @ColumnInfo(name = "tempDaily") val tempDaily: String,

    //Search
    @ColumnInfo(name = "nameLocSearch") val nameLocSearch: String,
    @ColumnInfo(name = "nameSearchView") val nameSearchView: String,
    @ColumnInfo(name = "tempSearchView") val tempSearchView: String,



)