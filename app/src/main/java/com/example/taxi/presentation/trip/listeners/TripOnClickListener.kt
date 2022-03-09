package com.example.taxi.presentation.trip.listeners

import com.example.taxi.presentation.trip.models.TripModel

interface TripOnClickListener {
    fun onClick(position: Int, item: TripModel)
}