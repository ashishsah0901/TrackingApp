package com.example.tracker.repositories

import com.example.tracker.db.Run
import com.example.tracker.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)
    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)
    fun getAllRunsSortedByDate() = runDAO.getAllRunsSortByDate()
    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortByDistance()
    fun getAllRunsSortedByTimeInMillis() = runDAO.getAllRunsSortByTimeMillis()
    fun getAllRunsSortedByAvgSpeed() = runDAO.getAllRunsSortByAvgSpeed()
    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortByCaloriesBurned()
    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()
    fun getTotalDistance() = runDAO.getTotalDistance()
    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()
    fun getTotalTImeInMillis() = runDAO.getTotalTimeInMillis()
}