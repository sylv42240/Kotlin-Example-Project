package com.sgranjon.kotlinexampleproject.data.manager.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import javax.inject.Inject

private const val SHARED_PREFS_KEY = "KOTLIN_EXAMPLE_PROJECT_PREFS"

private const val THEME_KEY = "THEME_KEY"

class SharedPrefManagerImpl @Inject constructor(context: Context) :
    SharedPrefManager {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

    override fun setTheme(mode: Int) {
        sharedPreferences.edit {
            putInt(THEME_KEY, mode)
        }
    }

    override fun getTheme(): Int {
        return sharedPreferences.getInt(THEME_KEY, AppCompatDelegate.MODE_NIGHT_NO)
    }

}