package com.example.tracker.other

import android.Manifest
import android.content.Context
import android.location.Location
import android.os.Build
import com.example.tracker.services.PolyLine
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

class TrackingUtility {
    companion object{
        fun hasLocationPermission(context: Context): Boolean {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                EasyPermissions.hasPermissions(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } else {
                EasyPermissions.hasPermissions(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )
            }
        }

        fun getFormattedStopWatchTime(ms: Long, includeMillis:Boolean = false): String{
            var millisSeconds = ms
            val hours = TimeUnit.MILLISECONDS.toHours(millisSeconds)
            millisSeconds -= TimeUnit.HOURS.toMillis(hours)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millisSeconds)
            millisSeconds -= TimeUnit.MINUTES.toMillis(minutes)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millisSeconds)
            if(!includeMillis){
                return "${if(hours<10) "0" else ""}$hours:" +
                        "${if(minutes<10) "0" else ""}$minutes:"+
                        "${if(seconds<10) "0" else ""}$seconds"
            }
            millisSeconds -= TimeUnit.SECONDS.toMillis(seconds)
            millisSeconds /= 10
            return "${if(hours<10) "0" else ""}$hours:" +
                    "${if(minutes<10) "0" else ""}$minutes:"+
                    "${if(seconds<10) "0" else ""}$seconds:" +
                    "${if(millisSeconds<10) "0" else ""}$millisSeconds"
        }

        fun calculatePolylineLength(polyLine: PolyLine): Float {
            var distance = 0f
            for(i in 0..polyLine.size-2){
                val pos1 = polyLine[i]
                val pos2 = polyLine[i+1]
                val result = FloatArray(1)
                Location.distanceBetween(
                    pos1.latitude,
                    pos1.longitude,
                    pos2.latitude,
                    pos2.longitude,
                    result
                )
                distance += result[0]
            }
            return distance
        }
    }
}