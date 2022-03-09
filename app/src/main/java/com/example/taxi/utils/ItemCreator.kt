package com.example.taxi.utils

import com.example.taxi.R
import com.example.taxi.presentation.trip.models.TripModel
import com.google.android.gms.maps.model.LatLng
import kotlin.random.Random

fun getTripHistory(): List<TripModel> {
    val list = ArrayList<TripModel>(7)
    list.add(
        TripModel(
            startAddress = "улица Sharof Rashidov, Ташкент",
            endAddress = "5a улица Асадуллы Ходжаева",
            time = "21:36",
            price = "12 900 cум",
            imageId = getRandomImageId(),
            date = "6 Июля, Вторник"
        )
    )

    list.add(
        TripModel(
            startAddress = "улица Sharof Rashidov, Ташкент",
            endAddress = "Юнусабадский район, м-в юнусабад-19",
            time = "19:24",
            price = "14 800 cум",
            imageId = getRandomImageId(),
            date = "6 Июля, Вторник"
        )
    )

    list.add(
        TripModel(
            startAddress = "улица Sharof Rashidov, Ташкент",
            endAddress = "5a улица Асадуллы Ходжаева",
            time = "21:36",
            price = "12 900 cум",
            imageId = getRandomImageId(),
            date = "6 Июля, Вторник"
        )
    )

    list.add(
        TripModel(
            startAddress = "улица Sharof Rashidov, Ташкент",
            endAddress = "5a улица Асадуллы Ходжаева",
            time = "10:42",
            price = "32 400 cум",
            imageId = getRandomImageId(),
            date = "6 Июля, Вторник"
        )
    )

    list.add(
        TripModel(
            startAddress = "Юнусабадский район, м-в юнусабад-19",
            endAddress = "5a улица Асадуллы Ходжаева",
            time = "21:36",
            price = "12 900 cум",
            imageId = getRandomImageId(),
            date = "5 Июля, Вторник"
        )
    )

    list.add(
        TripModel(
            startAddress = "Яшнабадский район, улица Sharof Rashidov",
            endAddress = "5a улица Асадуллы Ходжаева",
            time = "21:36",
            price = "12 900 cум",
            imageId = getRandomImageId(),
            date = "5 Июля, Вторник"
        )
    )

    list.add(
        TripModel(
            startAddress = "улица Sharof Rashidov, Ташкент",
            endAddress = "5a улица Асадуллы Ходжаева",
            time = "21:36",
            price = "12 900 cум",
            imageId = getRandomImageId(),
            date = "5 Июля, Вторник"
        )
    )

    return list
}

fun getRandomImageId(): Int {
    return when (Random.nextInt(0, 3)) {
        0 -> R.drawable.car1
        1 -> R.drawable.car2
        2 -> R.drawable.car3
        else -> R.drawable.car1
    }
}

fun getLocations(): List<LatLng> {
    val list = ArrayList<LatLng>(6)
    list.add(LatLng(41.293825, 69.246290))
    list.add(LatLng(41.29330466134932, 69.24775494834904))
    list.add(LatLng(41.29312731873478, 69.25082339517328))
    list.add(LatLng(41.299331089125864, 69.24948150790894))
    list.add(LatLng(41.304149055076564, 69.24737070732749))
    list.add(LatLng(41.30435915269068, 69.2468023314747))
    return list
}