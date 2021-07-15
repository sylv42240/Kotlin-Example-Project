package com.sgranjon.kotlinexampleproject.presentation.ui.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.map
import com.sgranjon.kotlinexampleproject.data.repository.CharacterRepository
import com.sgranjon.kotlinexampleproject.presentation.base.SingleLiveEvent
import com.sgranjon.kotlinexampleproject.presentation.base.viewmodel.BaseViewModel
import com.sgranjon.kotlinexampleproject.presentation.extensions.subscribeByIO
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    BaseViewModel() {

    private val characterPagingDataLiveData =
        MutableLiveData<PagingData<CharacterViewDataWrapper>>()
    private val errorLiveEvent = SingleLiveEvent<Throwable>()

    fun retrieveCharacterList() {
        characterRepository.retrieveCharacterList().subscribeByIO(
            onNext = { pagingData ->
                characterPagingDataLiveData.postValue(pagingData.map { CharacterViewDataWrapper(it) })
            },
            onError = {
                errorLiveEvent.postValue(it)
            },
            onComplete = {
                // Do nothing
            }
        ).addToComposite()
    }

    fun getCharacterPagingDataLiveData(): LiveData<PagingData<CharacterViewDataWrapper>> =
        characterPagingDataLiveData

    fun getErrorLiveEvent(): LiveData<Throwable> = errorLiveEvent

}