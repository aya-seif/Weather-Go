package com.app.weatheappworld.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val CREATE_TABLE = ("CREATE TABLE " + Config.TABLE_FAV + "("
                + Config.COLUMN_FAVOURITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_COUNTRY_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_COUNTRY_LAN + " TEXT NOT NULL, "
                + Config.COLUMN_COUNTRY_LON + " TEXT NOT NULL, "
                + Config.COLUMN_COUNTRY_TEMP + " TEXT "
                + ")")

        val CREATE_TABLE2 = ("CREATE TABLE " + Config.TABLE_ALARM + "("
                + Config.COLUMN_ALARM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_DATE_TIME + " TEXT , "
                + Config.COLUMN_TIME_FROM + " TEXT , "
                + Config.COLUMN_TIME_TO + " TEXT "
                + ")")
        val CREATE_TABLE3 = ("CREATE TABLE " + Config.TABLE_HOME + "("
                + Config.COLUMN_CLOUDS + " TEXT NOT NULL, "
                + Config.COLUMN_MIN_TEMP + " TEXT NOT NULL, "
                + Config.COLUMN_MAX_TEMP + " TEXT NOT NULL, "
                + Config.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + Config.COLUMN_TEMP_HOME + " TEXT NOT NULL, "
                + Config.COLUMN_DATE_HOME + " TEXT NOT NULL, "
                + Config.COLUMN_ICON_DAILY + " TEXT NOT NULL, "
                + Config.COLUMN_ICON_HOURLY + " TEXT NOT NULL, "
                + Config.COLUMN_TEMP_DAILY + " TEXT NOT NULL, "
                + Config.COLUMN_TEMP_HOURLY + " TEXT NOT NULL, "
                + Config.COLUMN_TIME_DAILY + " TEXT NOT NULL, "
                + Config.COLUMN_TIME_HOURLY + " TEXT NOT NULL, "
                + Config.COLUMN_HUMIDITY + " TEXT NOT NULL, "
                + Config.COLUMN_NAME_LOC_HOME + " TEXT NOT NULL, "
                + Config.COLUMN_PRESSURE + " TEXT NOT NULL, "
                + Config.COLUMN_WIND + " TEXT "
                + ")")

        db.execSQL(CREATE_TABLE)
        db.execSQL(CREATE_TABLE2)
        db.execSQL(CREATE_TABLE3)

        Log.d(TAG, "DB created!")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_FAV)
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_ALARM)
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_HOME)

        onCreate(db)
    }

    companion object {

        private val TAG = DatabaseHelper::class.java.simpleName
        private var databaseHelper: DatabaseHelper? = null

        private val DATABASE_VERSION = 11

        private val DATABASE_NAME = Config.DATABASE_NAME

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (databaseHelper == null) {
                databaseHelper = DatabaseHelper(context)
            }
            return databaseHelper!!
        }
    }

}
