package com.example.taxi.presentation.trip.models

data class TripModel(
    val startAddress: String,
    val endAddress: String,
    val time: String,
    val price: String,
    val imageId: Int,
    val date: String,
)