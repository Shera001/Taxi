package com.example.taxi.data.repository

import com.example.taxi.presentation.trip.models.TripModel
import com.example.taxi.utils.getTripHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TripHistoryRepository {

    suspend fun getTrips(): List<TripModel> = withContext(Dispatchers.IO) {
        delay(2000L)
        getTripHistory()
    }
}