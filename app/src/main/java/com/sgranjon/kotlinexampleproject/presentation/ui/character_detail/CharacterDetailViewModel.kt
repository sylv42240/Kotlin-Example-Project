package com.sgranjon.kotlinexampleproject.presentation.ui.character_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgranjon.kotlinexampleproject.data.repository.CharacterRepository
import com.sgranjon.kotlinexampleproject.presentation.base.SingleLiveEvent
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper
import com.sgranjon.kotlinexampleproject.presentation.wrapper.EpisodeViewDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharacterDetailViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {
    private val characterDetailLiveData = MutableLiveData<CharacterViewDataWrapper>()
    private val episodeListLiveData = MutableLiveData<List<EpisodeViewDataWrapper>>()
    private val errorLiveEvent = SingleLiveEvent<Throwable>()

    fun retrieveCharacterDetail(id: Int) {
        viewModelScope.launch {
            characterRepository.retrieveCharacterById(id).catch {
                errorLiveEvent.postValue(it)
            }.collect {
                characterDetailLiveData.postValue(CharacterViewDataWrapper(it))
            }
        }
    }

    fun retrieveCharacterEpisodeList(id: Int) {
        viewModelScope.launch {
            characterRepository.retrieveCharacterEpisodeList(id).catch {
                errorLiveEvent.postValue(it)
            }.collect { episodes ->
                episodeListLiveData.postValue(episodes.map { EpisodeViewDataWrapper(it) })
            }
        }
    }

    fun getCharacterDetailLiveData(): LiveData<CharacterViewDataWrapper> = characterDetailLiveData
    fun getEpisodeListLiveData(): LiveData<List<EpisodeViewDataWrapper>> = episodeListLiveData
    fun getErrorLiveEvent(): LiveData<Throwable> = errorLiveEvent
}