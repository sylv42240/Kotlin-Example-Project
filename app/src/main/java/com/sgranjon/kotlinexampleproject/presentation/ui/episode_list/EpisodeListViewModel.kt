package com.sgranjon.kotlinexampleproject.presentation.ui.episode_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sgranjon.kotlinexampleproject.data.repository.EpisodeRepository
import com.sgranjon.kotlinexampleproject.presentation.wrapper.EpisodeViewDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodeListViewModel @Inject constructor(private val episodeRepository: EpisodeRepository) :
    ViewModel() {
    private val episodePagingDataLiveData =
        MutableLiveData<PagingData<EpisodeViewDataWrapper>>()

    fun retrieveEpisodeList() {
        viewModelScope.launch {
            episodeRepository.retrieveEpisodeList().cachedIn(this).collectLatest { pagingData ->
                episodePagingDataLiveData.postValue(pagingData.map { EpisodeViewDataWrapper(it) })
            }
        }
    }

    fun getEpisodePagingDataLiveData(): LiveData<PagingData<EpisodeViewDataWrapper>> =
        episodePagingDataLiveData
}