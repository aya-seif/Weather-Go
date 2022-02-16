package com.app.weatheappworld.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.app.weatheappworld.databinding.FragmentSettingBinding
import com.app.weatheappworld.ui.activity.MainActivity
import android.widget.Toast
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.ConnectionResult
import android.content.ContentValues.TAG
import android.preference.ListPreference
import android.preference.Preference
import android.util.Log
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources

import android.util.DisplayMetrics
import java.util.*


class SettingFragment : Fragment() {

    var changeLanguage = false
    private lateinit var binding: FragmentSettingBinding
    private val ERROR_DIALOG_REQUEST = 9001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            if (isServicesOK()) {
//                init();
//            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater)
        return binding.root

    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity: MainActivity? = activity as MainActivity?

        binding.map.setOnCheckedChangeListener { compoundButton, b ->
//
            findNavController().navigate(SettingFragmentDirections.actionSettingFragmentToMapsFragment())
//
        }
        binding.location.setOnCheckedChangeListener { _, checkedId ->
//            if (checkedId == com.app.weatheappworld.R.id.map) {
//                init()
//            }
            if (checkedId == com.app.weatheappworld.R.id.gps) {
                findNavController().navigate(SettingFragmentDirections.actionSettingFragmentToFragmentHome())
                activity!!.saveSettingHome(true)
            }
        }

        binding.languages.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == com.app.weatheappworld.R.id.ar) {
                activity?.saveLang("ar")
                setLocale("ar")


            }
            if (checkedId == com.app.weatheappworld.R.id.en) {
                activity?.saveLang("en")
                setLocale("en")
            }
        }
        binding.unit.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == com.app.weatheappworld.R.id.c) {
                activity?.saveUnits("metric")
            }
            if (checkedId == com.app.weatheappworld.R.id.f) {
                activity?.saveUnits("imperial")
            }
        }

        binding.wind.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == com.app.weatheappworld.R.id.wind_s) {
                 activity?.saveUnits("metric")


            }
            if (checkedId == com.app.weatheappworld.R.id.wind_h) {
                activity?.saveUnits("imperial")

            }


        }


    }

    //    private fun init() {
//        binding.map.setOnCheckedChangeListener { compoundButton, b ->
//
//            findNavController().navigate(SettingFragmentDirections.actionSettingFragmentToMapsFragment())
//
//        }
//
//    }
    open fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res: Resources = resources
        val dm: DisplayMetrics = res.getDisplayMetrics()
        val conf: Configuration = res.getConfiguration()
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)

        val refresh = Intent(requireContext(),MainActivity::class.java)
        requireActivity().finish()
        startActivity(refresh)
    }

    fun isServicesOK(): Boolean {
        Log.d(TAG, "isServicesOK: checking google services version")
        val available =
            GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(requireContext())
        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working")
            return true
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it")
//            val dialog: Dialog = GoogleApiAvailability.getInstance()
//                .getErrorDialog(this.context.toString(),9001,9001)
//            dialog.show()
        } else {
            Toast.makeText(
                requireContext(),
                "You can't make map requests",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        return false
    }
}


