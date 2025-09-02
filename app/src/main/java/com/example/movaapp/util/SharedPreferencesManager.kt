package com.example.movaapp.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private const val PREFS_NAME = "MyAppPrefs"
    private const val KEY_FIRST_TIME = "is_first_time"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isFirstTime(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean(KEY_FIRST_TIME, true)
    }

    fun setFirstTime(context: Context, isFirstTime: Boolean) {
        val editor = getSharedPreferences(context).edit()
        editor.putBoolean(KEY_FIRST_TIME, isFirstTime)
        editor.apply()
    }
}