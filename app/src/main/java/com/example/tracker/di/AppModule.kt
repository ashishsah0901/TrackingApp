package com.example.tracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.tracker.db.RunningDatabase
import com.example.tracker.other.Constants.Companion.KEY_FIRST_TIME_TOGGLE
import com.example.tracker.other.Constants.Companion.KEY_NAME
import com.example.tracker.other.Constants.Companion.KEY_WEIGHT
import com.example.tracker.other.Constants.Companion.RUNNING_DATABASE_NAME
import com.example.tracker.other.Constants.Companion.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): RunningDatabase{
        return Room.databaseBuilder(app,RunningDatabase::class.java, RUNNING_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRunDao(db:RunningDatabase) = db.getRunDao()

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME,"")?:""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT,80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) = sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE,true)
}