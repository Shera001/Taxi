package com.example.taxi.presentation.trip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taxi.data.repository.TripHistoryRepository
import com.example.taxi.presentation.trip.models.TripModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor(repository: TripHistoryRepository) : ViewModel() {

    val tripHistory: SharedFlow<List<TripModel>> = flow {
        emit(repository.getTrips())
    }.shareIn(scope = viewModelScope, started = SharingStarted.Lazily, replay = 1)

    private val _shimmerVisible = MutableStateFlow(true)
    val shimmerVisible :StateFlow<Boolean> = _shimmerVisible.asStateFlow()

    fun setShimmerVisible(visible: Boolean) {
        _shimmerVisible.value = visible
    }
}