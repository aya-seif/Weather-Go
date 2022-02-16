package com.app.weatheappworld.ui.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weatheappworld.R
import com.app.weatheappworld.data.DatabaseClass
import com.app.weatheappworld.model.Alarm
import com.app.weatheappworld.simplealarm.AlarmAdapter
import com.app.weatheappworld.simplealarm.AlarmReceiver
import com.app.weatheappworld.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_alarm_scheduler.*
import kotlinx.android.synthetic.main.fragment_alert.*
import kotlinx.android.synthetic.main.fragment_alert.view.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatterBuilder
import java.util.*

class AlertFragment : Fragment() {
    private var date:String? = null
    private var from:String? = null
    private var to:String? = null
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_alert, container, false)

        root.recycleViewAlerts.layoutManager = LinearLayoutManager(activity)
        root.recycleViewAlerts.setHasFixedSize(true)

        val adapter = AlarmAdapter(
            activity!!.applicationContext,
            DatabaseClass(requireContext()).alarmList,
        )
        root.recycleViewAlerts.adapter = adapter
        adapter.notifyDataSetChanged()
        val activity: MainActivity? = activity as MainActivity?

        //setlect date
        root.datePicker.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                showDateDialog(this, root.datePicker)
//                activity?.updateConfig()
            }
        })

        //select time
        root.timePicker_from.setOnClickListener(View.OnClickListener { openTimePickerDialog("1") })
        root.timePicker_to.setOnClickListener(View.OnClickListener { openTimePickerDialog("2") })
       // close.setOnClickListener { _: View? -> activity?.finish() }

        root.save.setOnClickListener {
            insertAlarm()
            root.save.isEnabled = false
            @Suppress("DEPRECATION")
            Handler().postDelayed(
                {
                root.save.isEnabled = true
                }, 5000
            )
        }
        return root
    }
    private fun showDateDialog(onClickListener: View.OnClickListener, date_in: TextView) {
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
            date_in.text = simpleDateFormat.format(calendar.time)
            date = simpleDateFormat.format(calendar.time)
        }
        DatePickerDialog(requireContext(), dateSetListener, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]).show()
    }

    private fun openTimePickerDialog(t: String) {
        val calendar = Calendar.getInstance()
        if (t=="1") {
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                onTimeSetListener,
                calendar[Calendar.HOUR_OF_DAY],
                calendar[Calendar.MINUTE],
                true
            )
            timePickerDialog.setTitle("Set Alarm Time")
            timePickerDialog.show()
        }else{
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                onTimeSetListener2,
                calendar[Calendar.HOUR_OF_DAY],
                calendar[Calendar.MINUTE],
                true
            )
            timePickerDialog.setTitle("Set Alarm Time")
            timePickerDialog.show()
        }


    }

    private fun insertAlarm() {
        DatabaseClass(requireContext()).insertAlarm(
            Alarm(
                "",date,from,to
            )
        )
        val adapter = AlarmAdapter(
            activity!!.applicationContext,
            DatabaseClass(requireContext()).alarmList,
        )
        recycleViewAlerts.adapter = adapter
        adapter.notifyDataSetChanged()
        timePicker_from.text = ""
        timePicker_to.text = ""
        datePicker.text = ""
        Log.d("TAG","$date,$from,$to")
    }

    private var onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
        val now = Calendar.getInstance()
        val schedule = now.clone() as Calendar

        schedule[Calendar.HOUR_OF_DAY] = hourOfDay
        schedule[Calendar.MINUTE] = minute
        schedule[Calendar.SECOND] = 0
        schedule[Calendar.MILLISECOND] = 0

        if (schedule <= now) schedule.add(Calendar.DATE, 1)


        val simpleDateFormat = SimpleDateFormat("HH:mm")
        timePicker_from.text = simpleDateFormat.format(schedule.time)
        from = simpleDateFormat.format(schedule.time)

        //timePicker_from.text = schedule.time.toString()
        // setAlarm(schedule)
    }
    private var onTimeSetListener2 = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
        val now = Calendar.getInstance()
        val schedule = now.clone() as Calendar

        schedule[Calendar.HOUR_OF_DAY] = hourOfDay
        schedule[Calendar.MINUTE] = minute
        schedule[Calendar.SECOND] = 0
        schedule[Calendar.MILLISECOND] = 0

        if (schedule <= now) schedule.add(Calendar.DATE, 1)


        val simpleDateFormat = SimpleDateFormat("HH:mm")
        timePicker_to.text = simpleDateFormat.format(schedule.time)
        to = simpleDateFormat.format(schedule.time)
        //timePicker_from.text = schedule.time.toString()
        setAlarm(schedule)
    }

    private fun setAlarm(calendar: Calendar) {
        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent, 0)
        val alarmManager = (requireContext().getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager)

        alarmManager[AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = pendingIntent

        Toast.makeText(requireContext(), "Alarm Scheduled " + calendar.time, Toast.LENGTH_LONG).show()

    }

}