package com.sgranjon.kotlinexampleproject.presentation.ui.character.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sgranjon.kotlinexampleproject.data.repository.CharacterRepository
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {

    private val characterPagingDataLiveData =
        MutableLiveData<PagingData<CharacterViewDataWrapper>>()

    fun retrieveCharacterList() {
        viewModelScope.launch {
            characterRepository.retrieveCharacterList().cachedIn(this).collectLatest { pagingData ->
                characterPagingDataLiveData.postValue(pagingData.map { CharacterViewDataWrapper(it) })
            }
        }
    }

    fun getCharacterPagingDataLiveData(): LiveData<PagingData<CharacterViewDataWrapper>> =
        characterPagingDataLiveData


}