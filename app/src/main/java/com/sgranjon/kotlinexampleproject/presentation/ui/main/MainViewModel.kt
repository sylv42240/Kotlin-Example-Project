package com.sgranjon.kotlinexampleproject.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgranjon.kotlinexampleproject.data.repository.SettingsRepository
import com.sgranjon.kotlinexampleproject.presentation.base.SingleLiveEvent
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {
    private val errorLiveEvent = SingleLiveEvent<Throwable>()
    private val themeLiveEvent = SingleLiveEvent<Int>()

    fun updateTheme(mode: Int){
        viewModelScope.launch {
            settingsRepository.updateTheme(mode).catch {
                errorLiveEvent.postValue(it)
            }.collect {
                themeLiveEvent.postValue(it)
            }
        }
    }

    fun retrieveCurrentTheme(){
        viewModelScope.launch {
            settingsRepository.getCurrentTheme().catch {
                errorLiveEvent.postValue(it)
            }.collect {
                themeLiveEvent.postValue(it)
            }
        }
    }

    fun getThemeLiveEvent(): LiveData<Int> = themeLiveEvent
    fun getErrorLiveEvent(): LiveData<Throwable> = errorLiveEvent
}