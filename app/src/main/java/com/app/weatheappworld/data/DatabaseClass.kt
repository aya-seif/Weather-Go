package com.app.weatheappworld.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import android.widget.Toast
import com.app.weatheappworld.model.Alarm
import com.app.weatheappworld.model.Fav
import com.app.weatheappworld.model.local.HomeInfo

import java.util.ArrayList

class DatabaseClass(private val context: Context) {
    private val TAG = DatabaseClass::class.java.simpleName

    private val list: ArrayList<Fav>
        @SuppressLint("Range")
        get() {

            val databaseHelper = DatabaseHelper.getInstance(context)
            val sqLiteDatabase = databaseHelper.readableDatabase

            var cursor: Cursor? = null

            try {
                cursor = sqLiteDatabase.query(Config.TABLE_FAV, null, null, null, null, null, null, null)

                if (cursor != null)
                    if (cursor.moveToFirst()) {
                        val orderList = ArrayList<Fav>()
                        do {
                            val id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_FAVOURITE_ID))
                            val name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTRY_NAME))
                            val lan = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTRY_LAN))
                            val lon = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTRY_LON))
                            val temp = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTRY_TEMP))

                            orderList.add(Fav(id.toString(),name,lan.toDouble(),lon.toDouble(), temp))

                        } while (cursor.moveToNext())

                        return orderList
                    }
            } catch (e: Exception) {
                Log.d(TAG, "Exception: " + e.message)
                Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show()
            } finally {
                cursor?.close()
                sqLiteDatabase.close()
            }

            return ArrayList()
        }

     val alarmList: ArrayList<Alarm>
        @SuppressLint("Range")
        get() {

            val databaseHelper = DatabaseHelper.getInstance(context)
            val sqLiteDatabase = databaseHelper.readableDatabase

            var cursor: Cursor? = null

            try {
                cursor = sqLiteDatabase.query(Config.TABLE_ALARM, null, null, null, null, null, null, null)

                if (cursor != null)
                    if (cursor.moveToFirst()) {
                        val list = ArrayList<Alarm>()
                        do {
                            val id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ALARM_ID))
                            val date = cursor.getString(cursor.getColumnIndex(Config.COLUMN_DATE_TIME))
                            val timeFrom = cursor.getString(cursor.getColumnIndex(Config.COLUMN_TIME_FROM))
                            val timeTo = cursor.getString(cursor.getColumnIndex(Config.COLUMN_TIME_TO))

                            list.add(Alarm(id.toString(), date,timeFrom, timeTo))

                        } while (cursor.moveToNext())

                        list.reverse()
                        return list
                    }
            } catch (e: Exception) {
                Log.d(TAG, "Exception: " + e.message)
                Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show()
            } finally {
                cursor?.close()
                sqLiteDatabase.close()
            }

            return ArrayList()
        }

//    val homelist: ArrayList<HomeInfo>
//        @SuppressLint("Range")
//        get() {
//
//            val databaseHelper = DatabaseHelper.getInstance(context)
//            val sqLiteDatabase = databaseHelper.readableDatabase
//
//            var cursor: Cursor? = null
//
//            try {
//                cursor = sqLiteDatabase.query(Config.TABLE_HOME, null, null, null, null, null, null, null)
//
//                if (cursor != null)
//                    if (cursor.moveToFirst()) {
//                        val list = ArrayList<HomeInfo>()
//                        do {
//                            val id = cursor.getString(cursor.getColumnIndex(Config.))
//                            val date = cursor.getString(cursor.getColumnIndex(Config.COLUMN_DATE_TIME))
//                            val timeFrom = cursor.getString(cursor.getColumnIndex(Config.COLUMN_TIME_FROM))
//                            val timeTo = cursor.getString(cursor.getColumnIndex(Config.COLUMN_TIME_TO))
//
//                            list.add()
//
//                        } while (cursor.moveToNext())
//
//                        list.reverse()
//                        return list
//                    }
//            } catch (e: Exception) {
//                Log.d(TAG, "Exception: " + e.message)
//                Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show()
//            } finally {
//                cursor?.close()
//                sqLiteDatabase.close()
//            }
//
//            return ArrayList()
//        }


    fun insert(fav: Fav): Long {

        var id: Long = -1
        val databaseHelper = DatabaseHelper.getInstance(context)
        val sqLiteDatabase = databaseHelper.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(Config.COLUMN_COUNTRY_NAME, fav.name)
        contentValues.put(Config.COLUMN_COUNTRY_LAN, fav.lat)
        contentValues.put(Config.COLUMN_COUNTRY_LON, fav.lon)
        contentValues.put(Config.COLUMN_COUNTRY_TEMP, fav.temp)

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_FAV, null, contentValues)
            Toast.makeText(context, "insert successfully ", Toast.LENGTH_LONG).show()

        } catch (e: SQLiteException) {
            Log.d(TAG, "Exception: " + e.message)
            Toast.makeText(context, "Operation failed: " + e.message, Toast.LENGTH_LONG).show()
        } finally {
            sqLiteDatabase.close()
        }

        return id
    }
    fun insertAlarm(alarm: Alarm): Long {

        var id: Long = -1
        val databaseHelper = DatabaseHelper.getInstance(context)
        val sqLiteDatabase = databaseHelper.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(Config.COLUMN_DATE_TIME, alarm.date)
        contentValues.put(Config.COLUMN_TIME_FROM, alarm.from)
        contentValues.put(Config.COLUMN_TIME_TO, alarm.to)

        try {
            id = sqLiteDatabase.insertOrThrow(Config.TABLE_ALARM, null, contentValues)
            Toast.makeText(context, "insert successfully to alarm ", Toast.LENGTH_LONG).show()

        } catch (e: SQLiteException) {
            Log.d(TAG, "Exception: " + e.message)
            Toast.makeText(context, "mina " + e.message, Toast.LENGTH_LONG).show()
        } finally {
            sqLiteDatabase.close()
        }

        return id
    }



    @SuppressLint("Range")
    fun getById(idOfPerson: Long): Fav? {

        val databaseHelper = DatabaseHelper.getInstance(context)
        val sqLiteDatabase = databaseHelper.readableDatabase

        var cursor: Cursor? = null
        var person: Fav? = null
        try {

            cursor = sqLiteDatabase.query(
                Config.TABLE_FAV, null,
                Config.COLUMN_FAVOURITE_ID + " = ? ", arrayOf(idOfPerson.toString()), null, null, null
            )

            if (cursor!!.moveToFirst()) {
                val id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_FAVOURITE_ID))
                val name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTRY_NAME))
                val lan = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTRY_LAN))
                val lon = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTRY_LON))
                val temp = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTRY_TEMP))

                list.add(Fav(id.toString(), name, lan.toDouble(),lon.toDouble(),temp, ))
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception: " + e.message)
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show()
        } finally {
            cursor?.close()
            sqLiteDatabase.close()
        }

        return person
    }

    fun update(fav: Fav): Long {

        var rowCount: Long = 0
        val databaseHelper = DatabaseHelper.getInstance(context)
        val sqLiteDatabase = databaseHelper.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(Config.COLUMN_COUNTRY_NAME, fav.name)
        contentValues.put(Config.COLUMN_COUNTRY_LAN, fav.lat)
        contentValues.put(Config.COLUMN_COUNTRY_LON, fav.lon)
        contentValues.put(Config.COLUMN_COUNTRY_TEMP, fav.temp)

        try {
            rowCount = sqLiteDatabase.update(
                Config.TABLE_FAV, contentValues,
                Config.COLUMN_FAVOURITE_ID + " = ? ",
                arrayOf(fav.id.toString())
            ).toLong()
        } catch (e: SQLiteException) {
            Log.d(TAG, "Exception: " + e.message)
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            sqLiteDatabase.close()
        }

        return rowCount
    }

    fun deleteById(idOfOrder: Long): Long {
        var deletedRowCount: Long = -1
        val databaseHelper = DatabaseHelper.getInstance(context)
        val sqLiteDatabase = databaseHelper.writableDatabase
        try {
            deletedRowCount = sqLiteDatabase.delete(
                Config.TABLE_FAV,
                Config.COLUMN_FAVOURITE_ID + " = ? ",
                arrayOf(idOfOrder.toString())
            ).toLong()
            Toast.makeText(context, "Order deleted from cart", Toast.LENGTH_LONG).show()
        } catch (e: SQLiteException) {
            Log.d(TAG, "Exception: " + e.message)
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            sqLiteDatabase.close()
        }

        return deletedRowCount
    }

    fun deleteAlarmById(idOfOrder: Long): Long {
        var deletedRowCount: Long = -1
        val databaseHelper = DatabaseHelper.getInstance(context)
        val sqLiteDatabase = databaseHelper.writableDatabase
        try {
            deletedRowCount = sqLiteDatabase.delete(
                Config.TABLE_ALARM,
                Config.COLUMN_ALARM_ID + " = ? ",
                arrayOf(idOfOrder.toString())
            ).toLong()
            Toast.makeText(context, "Alarm deleted from cart", Toast.LENGTH_LONG).show()
        } catch (e: SQLiteException) {
            Log.d(TAG, "Exception: " + e.message)
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        } finally {
            sqLiteDatabase.close()
        }

        return deletedRowCount
    }

}