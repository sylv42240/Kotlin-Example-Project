package com.sgranjon.kotlinexampleproject.presentation.ui.location_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sgranjon.kotlinexampleproject.data.repository.LocationRepository
import com.sgranjon.kotlinexampleproject.presentation.wrapper.LocationViewDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationListViewModel @Inject constructor(private val locationRepository: LocationRepository) :
    ViewModel() {
    private val locationPagingDataLiveData =
        MutableLiveData<PagingData<LocationViewDataWrapper>>()

    fun retrieveLocationList() {
        viewModelScope.launch {
            locationRepository.retrieveLocationList().cachedIn(this).collectLatest { pagingData ->
                locationPagingDataLiveData.postValue(pagingData.map { LocationViewDataWrapper(it) })
            }
        }
    }

    fun getLocationPagingDataLiveData(): LiveData<PagingData<LocationViewDataWrapper>> =
        locationPagingDataLiveData
}