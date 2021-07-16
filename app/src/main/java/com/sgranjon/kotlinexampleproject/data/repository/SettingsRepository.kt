package com.sgranjon.kotlinexampleproject.data.repository

import com.sgranjon.kotlinexampleproject.data.business.SettingsBusinessHelper
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

@Reusable
class SettingsRepository @Inject constructor(
    private val settingsBusinessHelper: SettingsBusinessHelper
) {
    fun updateTheme(mode: Int) = flow {
        settingsBusinessHelper.updateTheme(mode)
        emit(mode)
    }

    fun getCurrentTheme() = flow {
        emit(settingsBusinessHelper.getCurrentTheme())
    }
}
