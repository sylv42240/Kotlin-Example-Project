package com.sgranjon.kotlinexampleproject.presentation.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class EnumStateViewModel<T : Enum<T>> : BaseViewModel() {

    protected abstract var currentViewState: T

    private val viewState = MutableLiveData<T>()

    protected fun updateViewState(state: T) {
        viewState.postValue(state.also { currentViewState = it })
    }

    fun getViewState(): LiveData<T> = viewState
}