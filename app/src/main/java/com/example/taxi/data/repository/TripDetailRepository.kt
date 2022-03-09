package com.example.taxi.data.repository

import com.example.taxi.utils.getLocations
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TripDetailRepository {

    suspend fun getRoad(): List<LatLng> = withContext(Dispatchers.IO) {
        getLocations()
    }
}