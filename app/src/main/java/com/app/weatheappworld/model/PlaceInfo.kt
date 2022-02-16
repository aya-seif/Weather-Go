package com.app.weatheappworld.model

import android.net.Uri
import com.google.android.gms.maps.model.LatLng

class PlaceInfo(
    val name: String? = null,
    val address: String? = null,
    val phoneNumber: String? = null,
    val id: String? = null,
    val websiteUri: Uri? = null,
    val latlng: LatLng? = null,
    val rating: Float = 0f,
    val attributions: String? = null,
) {
}