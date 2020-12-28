package com.example.tracker.ui

import android.annotation.SuppressLint
import android.content.Context
import com.example.tracker.db.Run
import com.example.tracker.other.TrackingUtility
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.marker_view.view.*
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ViewConstructor")
class CustomMarkerView(
        val runs: List<Run>,
        c: Context,
        layoutId: Int
) : MarkerView(c,layoutId) {

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if(e==null){
            return
        }
        val curRunId = e.x.toInt()
        val run = runs[curRunId]

        val calender = Calendar.getInstance().apply {
            timeInMillis = run.timestamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        tvDate.text = dateFormat.format(calender.time)
        "${run.avgSpeedInKMH}km/h".also {
            tvAvgSpeed.text = it
        }
        val distanceInKm = "${run.distanceInMeters / 1000f}km"
        tvDistance.text = distanceInKm
        tvDuration.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)
        val caloriesBurned = "${run.caloriesBurned}kcal"
        tvCaloriesBurned.text = caloriesBurned
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-width/2f, -height.toFloat())
    }
}