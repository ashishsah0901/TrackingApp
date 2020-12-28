package com.example.tracker.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.tracker.repositories.MainRepository

class StatisticsViewModel @ViewModelInject constructor(
        mainRepository: MainRepository
) : ViewModel() {

    val totalTimeRun = mainRepository.getTotalTImeInMillis()
    val totalDistance = mainRepository.getTotalDistance()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()

    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
}