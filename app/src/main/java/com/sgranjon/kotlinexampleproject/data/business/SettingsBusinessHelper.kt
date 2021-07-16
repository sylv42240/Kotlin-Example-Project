package com.sgranjon.kotlinexampleproject.data.business

import com.sgranjon.kotlinexampleproject.data.manager.preferences.SharedPrefManager
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SettingsBusinessHelper @Inject constructor(private val sharedPrefManager: SharedPrefManager) {
    fun updateTheme(mode: Int) {
        sharedPrefManager.setTheme(mode)
    }

    fun getCurrentTheme() = sharedPrefManager.getTheme()
}