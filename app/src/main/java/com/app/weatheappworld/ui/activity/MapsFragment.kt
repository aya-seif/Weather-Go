package com.app.weatheappworld.ui.activity

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.weatheappworld.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.app.weatheappworld.data.DatabaseClass
import com.app.weatheappworld.databinding.FragmentAlertBinding
import com.app.weatheappworld.databinding.FragmentMapsBinding
import com.app.weatheappworld.model.Fav
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener

import com.google.android.gms.maps.model.Marker
import java.io.IOException
import java.util.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory





class MapsFragment : Fragment() ,LocationListener{
    private lateinit var mMap: GoogleMap
    var currentMarker: Marker? = null
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    var currentLocation: Location? = null
    var latitude: Double? = null
    var longtitue: Double? = null
    var Location: String? = null

    private lateinit var binding : FragmentMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            binding.map.setMyLocationEnabled(true)
//            map.setMapType(GoogleMap.MAP_TYPE_NORMAL)
//            map.addMarker(
//                MarkerOptions()
//                    .position(LatLng(0, 0))
//                    .title("Marker")
//                    .draggable(true)
//                    .snippet("Hello")
//                    .icon(
//                        BitmapDescriptorFactory
//                            .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
//                    )
//            )

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
//            fetchLocation()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMapsBinding.inflate(inflater)
        return binding.root
    }


    // get Current location
    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
               this.requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1000
            )
            return
        }

        val task = fusedLocationProviderClient?.lastLocation
        task?.addOnSuccessListener { location ->
            if (location != null) {
                this.currentLocation = location
//                val mapFragment = requireActivity().b.supportFragmentManager
//                    .findFragmentById(R.id.map) as SupportMapFragment
//                mapFragment.getMapAsync(this)

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1000 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            }
        }
    }

    fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapClickListener (object :
        GoogleMap.OnMapClickListener{
            override fun onMapClick(p0: LatLng) {
               Log.d("Map_Tag","CLICK")
            }
        })


        // current location
        var latLong = LatLng(currentLocation?.latitude!!, currentLocation?.longitude!!)
        drawMarker(latLong)

        mMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDrag(p0: Marker) {
                TODO("Not yet implemented")
            }

            override fun onMarkerDragEnd(p0: Marker) {
                TODO("Not yet implemented")
            }

            override fun onMarkerDragStart(p0: Marker) {
                if (currentMarker != null)
                    currentMarker?.remove()

                val newLatlong = LatLng(p0?.position!!.latitude, p0?.position!!.longitude)
                drawMarker(newLatlong)



                latitude = p0?.position.latitude
                longtitue = p0?.position.longitude
                Log.i("Lat", "${latitude}")
                Log.i("Lat", "${longtitue}")
                Log.i("Lat", "${Location}")

                DatabaseClass(requireContext()).insert(
                    Fav(
                        "",
                        "",
                        latitude,
                        longtitue,
                        Location,
                    )
                )
                requireActivity().finish()
            }
        })
    }

    private fun drawMarker(latLng: LatLng) {
        val markerOption = MarkerOptions().position(latLng).title("Your Location")
            .snippet(getAddress(latLng.latitude, latLng.longitude)).draggable(true)

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        currentMarker = mMap.addMarker(markerOption)
        currentMarker?.showInfoWindow()

    }

    private fun getAddress(lat: Double, long: Double): String? {
        val geCoder = Geocoder(requireContext(), Locale.getDefault())
        val address = geCoder.getFromLocation(lat, long, 1)
        if (address != null) {
            Location = (address[0].countryName)
        }
        return address[0].getAddressLine(0).toString()

    }

    override fun onLocationChanged(p0: Location) {


    }
}