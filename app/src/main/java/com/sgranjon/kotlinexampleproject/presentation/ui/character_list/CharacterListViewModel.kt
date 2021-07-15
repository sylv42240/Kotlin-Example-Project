package com.sgranjon.kotlinexampleproject.presentation.ui.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sgranjon.kotlinexampleproject.data.repository.CharacterRepository
import com.sgranjon.kotlinexampleproject.presentation.base.SingleLiveEvent
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    ViewModel() {

    private val characterPagingDataLiveData =
        MutableLiveData<PagingData<CharacterViewDataWrapper>>()
    private val errorLiveEvent = SingleLiveEvent<Throwable>()

    @ExperimentalCoroutinesApi
    fun retrieveCharacterList() {
        viewModelScope.launch {
            characterRepository.retrieveCharacterList().catch {
                errorLiveEvent.postValue(it)
            }.cachedIn(this).collectLatest { pagingData ->
                pagingData.map { println(it.name) }
                characterPagingDataLiveData.postValue(pagingData.map { CharacterViewDataWrapper(it) })
            }
        }
    }

    fun getCharacterPagingDataLiveData(): LiveData<PagingData<CharacterViewDataWrapper>> =
        characterPagingDataLiveData

    fun getErrorLiveEvent(): LiveData<Throwable> = errorLiveEvent

}