package com.example.taxi.presentation.trip_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taxi.data.repository.TripDetailRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class TripDetailViewModel @Inject constructor(repository: TripDetailRepository) : ViewModel() {

    val roadList: SharedFlow<List<LatLng>> = flow {
        emit(repository.getRoad())
    }.shareIn(scope = viewModelScope, started = SharingStarted.Lazily, replay = 1)
}