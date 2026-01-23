package com.musauyumaz.nutritionbook.util

import android.content.Context
import android.content.SharedPreferences

class PrivacySharedPreferences {
    companion object {
        private const val TIME = "time"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: PrivacySharedPreferences? = null

        private val lock = Any()

        operator fun invoke(context: Context): PrivacySharedPreferences =
            instance ?: synchronized(lock) {
                instance ?: createPrivacySharedPreferences(context).also {
                    instance = it
                }
            }

        private fun createPrivacySharedPreferences(context: Context): PrivacySharedPreferences {
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return PrivacySharedPreferences()
        }
    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit()?.putLong(TIME, time)?.apply()
    }
    fun getTime() = sharedPreferences?.getLong(TIME, 0)
}