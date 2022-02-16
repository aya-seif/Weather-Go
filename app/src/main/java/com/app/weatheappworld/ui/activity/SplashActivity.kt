package com.app.weatheappworld.ui.activity

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.weatheappworld.R
import com.app.weatheappworld.ui.BaseActivity
import com.app.weatheappworld.ui.fragments.FragmentHome

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // set fullscreen in android r
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }


        @Suppress("DEPRECATION")
        Handler().postDelayed(
            {
//                getLocation()
                if (checkPrefData()) {
                    //launch the main activity
                    startActivity(
                        Intent(this@SplashActivity,
                            MainActivity::class.java)
                    )
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

            }, 3000
        )
    }

//    private fun getLocation() {
//
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                FragmentHome.PERMISSION_REQUEST_ACCESS_FINE_LOCATION
//            )
//            return
//        }
//        var locationListener = object : LocationListener {
//            override fun onLocationChanged(p0: Location) {
//                Log.d(ContentValues.TAG, "getLocation: ${ p0?.latitude}>>${p0?.longitude}")
//               p0?.latitude
//               p0?.longitude
//
//            }
//
//        }
//
//
//        var locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager?
//
//        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
//        LocationListener { p0 ->
//            Log.d(ContentValues.TAG, "getLocation: ${ p0?.latitude}>>${p0?.longitude}")
//            p0?.latitude
//            p0?.longitude
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == FragmentHome.PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
//            when (grantResults[0]) {
//                PackageManager.PERMISSION_GRANTED -> getLocation()
//                PackageManager.PERMISSION_DENIED -> Toast.makeText(
//                    this,
//                    "Please Check Your Permission accepted",
//                    Toast.LENGTH_SHORT
//                )
//                    .show()
//            }
//        }
//    }
}