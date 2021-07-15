package com.sgranjon.kotlinexampleproject.presentation.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class EnumStateViewModel<T : Enum<T>> : ViewModel() {

    protected abstract var currentViewState: T

    private val viewState = MutableLiveData<T>()

    protected fun updateViewState(state: T) {
        viewState.postValue(state.also { currentViewState = it })
    }

    fun getViewState(): LiveData<T> = viewState
}