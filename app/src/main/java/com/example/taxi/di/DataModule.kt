package com.example.taxi.di

import com.example.taxi.data.repository.TripDetailRepository
import com.example.taxi.data.repository.TripHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun provideTripHistoryRepository(): TripHistoryRepository = TripHistoryRepository()

    @Provides
    fun provideTripDetailRepository(): TripDetailRepository = TripDetailRepository()
}