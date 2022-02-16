package com.app.weatheappworld.simplealarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.util.*
import androidx.databinding.DataBindingUtil
import com.app.weatheappworld.R
import com.app.weatheappworld.databinding.ActivityAlarmSchedulerBinding
import com.app.weatheappworld.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_alarm_scheduler.*

class AlarmScheduler : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_alarm_scheduler)


    }

    private fun openTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
                this,
                onTimeSetListener,
                calendar[Calendar.HOUR_OF_DAY],
                calendar[Calendar.MINUTE],
                true)
        timePickerDialog.setTitle("Set Alarm Time")
        timePickerDialog.show()
    }

    private var onTimeSetListener = OnTimeSetListener { _, hourOfDay, minute ->
        val now = Calendar.getInstance()
        val schedule = now.clone() as Calendar

        schedule[Calendar.HOUR_OF_DAY] = hourOfDay
        schedule[Calendar.MINUTE] = minute
        schedule[Calendar.SECOND] = 0
        schedule[Calendar.MILLISECOND] = 0

        if (schedule <= now) schedule.add(Calendar.DATE, 1)

        editTextTimePicker.setText(schedule.time.toString())
        setAlarm(schedule)
    }

    private fun setAlarm(calendar: Calendar) {
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        val alarmManager = (getSystemService(ALARM_SERVICE) as AlarmManager)

        alarmManager[AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = pendingIntent

        Toast.makeText(this, "Alarm Scheduled " + calendar.time, Toast.LENGTH_LONG).show()
    }
}