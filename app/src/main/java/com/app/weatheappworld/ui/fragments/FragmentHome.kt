package com.app.weatheappworld.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.weatheappworld.R
import com.app.weatheappworld.databinding.FragmentHomeBinding
import com.app.weatheappworld.remote.Daily
import com.app.weatheappworld.remote.Hourly
import com.app.weatheappworld.remote.ResponseWeather
import com.app.weatheappworld.ui.activity.MainActivity
import com.app.weatheappworld.ui.fragments.adapter.DailyAdapter
import com.app.weatheappworld.ui.fragments.adapter.HourlyAdapter
import com.app.weatheappworld.ui.fragments.viewmodels.HomeViewModel
import com.app.weatheappworld.utils.ResourceWeather
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class FragmentHome : Fragment() {
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    var hourlyListAdapter = HourlyAdapter(arrayListOf())
    var dailyListAdapter = DailyAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: MainActivity? = activity as MainActivity?
        if (activity!!.checkSettingHome()) {
            getLocation()
        }
        fun updateHourlyListUI(it: List<Hourly>) {
            hourlyListAdapter.updateHoursList(it, activity!!)
        }
        binding.settingBtn.setOnClickListener {
            findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToSettingFragment())
        }
        binding.favBtn.setOnClickListener {
            findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToFavoriteFragment())
        }
        binding.alertBtn.setOnClickListener {
            findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToAlertFragment())
        }

        fun updateDailyListUI(it: List<Daily>) {
            dailyListAdapter.updateDailyList(it)
        }

        if (!activity!!.checkPrefData()) {
            startSetting()
        }
        getLocation()
        try {
            getLocation()
            val args: FragmentHomeArgs by navArgs()

            activity.saveLang(args.lat)
            activity.saveLon(args.lon)
            viewModel.getResponse(
                activity.getLat()!!.toDouble(),
                activity.getLon()!!.toDouble(),
                Constants.KEY,
                activity.getUnits().toString(),
                activity.getLang()!!,
                "minutly"
            )
//            Log.d(
//                "TAG",
//                "current Loc: ${activity.getLat()!!.toDouble()} || ${
//                    activity.getLon()!!.toDouble()
//                }"
//            )

        } catch (e: Exception) {
//            Toast.makeText(requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.adapter = hourlyListAdapter
        binding.daily.adapter = dailyListAdapter

        viewModel.liveDataResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                ResourceWeather.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                ResourceWeather.Status.SUCCESS -> {

                    binding.progressBar.visibility = View.GONE
                    binding.decription.text =
                        it.data?.current?.weather?.get(0)?.description.toString()
                    val tz = TimeZone.getDefault()
                    val calendar = Calendar.getInstance()
                    calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
                    val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm a", Locale.getDefault())
                    val currenTimeZone =
                        (it.data?.current?.dt?.toLong())?.times(1000)?.let { it1 -> Date(it1) }
                    binding.date.text = sdf.format(currenTimeZone)
                    binding.textView.text = it.data?.timezone?.substringAfter("/")
                    hourlyListAdapter.updateHoursList(it.data!!.hourly, activity!!)
                    dailyListAdapter.updateDailyList(it.data!!.daily)
                    binding.timePressure.text =
                        it.let { it.data!!.current.pressure.toString() + "ps" }
                    binding.humidity.text = it.data?.current.humidity.toString() + "%"
                     val options = RequestOptions().error(R.mipmap.ic_launcher_round)
                     val icon: String? = it.let{it.data?.current?.weather?.get(0)?.icon}
                     it.let { Glide.with(requireActivity()).setDefaultRequestOptions(options)
                          .load("http://openweathermap.org/img/w/${icon}.png")
                           .into(binding.mainIcon)}


//                    binding.timeWind.text =
//                        (it.let { it.data!!.current?.windSpeed?.toInt().toString() }) + "m/s"
                    binding.clouds.text =
                        getText(R.string.clouds).toString() + (it.let { it.data!!.current.clouds.toString() }) + "%"
                    //TODO

                    when (activity.getUnits().toString()) {
                        "imperial" -> {
                            binding.temperature.text =
                                it.let { it.data!!.current.temp.toString() } + "${getText(R.string.fahrenheit)}"
                            binding.timeWind.text =
                                (it.let { it.data!!.current.windSpeed.toString() }+getText(R.string.mile_h))

//                            var wind: String = it.data.current.windSpeed.toString()
//                            activity.saveWind(wind)
                            binding.maxTemp.text =
                                (it.let {
                                    it.data!!.daily?.get(0)?.temp?.max?.toInt().toString() + "°F"
                                })
                            binding.minTemp.text =
                                (it.let {
                                    it.data!!.daily?.get(0)?.temp?.min?.toInt().toString() + "°F"
                                })

                        }
                        "metric" -> {
                            binding.temperature.text =
                                it.let { it.data!!.current.temp.toString() } + "${getText(R.string.c)}"
                            binding.timeWind.text =
                                (it.let { it.data!!.current.windSpeed.toString() }) + getText(
                                    R.string.ms
                                )
                            binding.maxTemp.text =
                                (it.let {
                                    it.data!!.daily?.get(0)?.temp?.max?.toInt().toString() + "°C"
                                })
                            binding.minTemp.text =
                                (it.let {
                                    it.data!!.daily?.get(0)?.temp?.min?.toInt().toString() + "°C"
                                })
                        }
                        else -> binding.temperature.text =
                            it.let { it.data!!.current.temp.toString() } + "${getText(R.string.c)}"
                    }
                }
                ResourceWeather.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })


//            val options = RequestOptions()
//                .error(R.mipmap.ic_launcher_round)
//            val icon: String? = it.current?.weather?.get(0)?.icon
//            this?.let { it1 ->
//                Glide.with(it1)
//                    .setDefaultRequestOptions(options)
//                    .load(icon?.let { it2 -> getIcon(it2) })
//                    .into(binding.)
//
//            }


        fun getIcon(icon: String): String {
            return "http://openweathermap.org/img/w/${icon}.png"
        }

        @SuppressLint("SetTextI18n")
        fun updateMainDetails(it: ResponseWeather) {
            val options = RequestOptions()
                .error(R.mipmap.ic_launcher_round)
            val icon: String? = it.current?.weather?.get(0)?.icon
            this?.let { it1 ->
                Glide.with(it1)
                    .setDefaultRequestOptions(options)
                    .load("http://openweathermap.org/img/w/${icon}.png")
                    .into(binding.mainIcon)
            }
        }
    }

    fun startSetting() {

        val activity: MainActivity? = activity as MainActivity?
        val dialog = Dialog(requireActivity())
        dialog.setCancelable(true)
        val view: View =
            activity?.layoutInflater!!.inflate(R.layout.dialog_settings, null)
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(view)
        val location = view.findViewById<View>(R.id.location) as RadioGroup
        val languages = view.findViewById<View>(R.id.languages) as RadioGroup
        val wind = view.findViewById<View>(R.id.wind) as RadioGroup
        val unit = view.findViewById<View>(R.id.unit) as RadioGroup
        val gps = view.findViewById<View>(R.id.gps) as RadioButton
        val map = view.findViewById<View>(R.id.map) as RadioButton
        val windH = view.findViewById<View>(R.id.wind_h) as RadioButton
        val windS = view.findViewById<View>(R.id.wind_s) as RadioButton
        val langAR = view.findViewById<View>(R.id.ar) as RadioButton
        val langEN = view.findViewById<View>(R.id.en) as RadioButton
        val unitC = view.findViewById<View>(R.id.c) as RadioButton
        val unitF = view.findViewById<View>(R.id.f) as RadioButton
        val finished = view.findViewById<View>(R.id.finished) as Button


        languages.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.ar) {
                activity.saveLang("ar")
            }
            if (checkedId == R.id.en) {
                activity.saveLang("en")

            }

        }

        finished.setOnClickListener {
            activity.savePrefsData(true)
            dialog.dismiss()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            activity.finish()
        }
        dialog.show()

        unit.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.c) {
                activity.saveUnits("metric")
            }
            if (checkedId == R.id.f) {
                activity.saveUnits("imperial")
            }
        }
        dialog.show()


    }

    private fun getLocation() {
        val activity: MainActivity? = activity as MainActivity?

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION
            )
            return
        }
        var locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                Log.d(TAG, "getLocation: ${p0?.latitude}>>${p0?.longitude}")
                latitude = p0?.latitude
                longitude = p0?.longitude
                activity!!.saveLat(latitude.toString())
                activity.saveLon(longitude.toString())
            }

            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {}

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        }


        var locationManager =
            requireContext().getSystemService(LOCATION_SERVICE) as LocationManager?

        locationManager!!.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0L,
            0f,
            locationListener
        )
        LocationListener { p0 ->
            Log.d(TAG, "getLocation: ${p0?.latitude}>>${p0?.longitude}")
            latitude = p0?.latitude
            longitude = p0?.longitude
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLocation()
                PackageManager.PERMISSION_DENIED -> Toast.makeText(
                    requireContext(),
                    "Please Check Your Permission accepted",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    companion object {
        const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }


}