package com.sgranjon.kotlinexampleproject.presentation.ui.character_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sgranjon.kotlinexampleproject.data.repository.CharacterRepository
import com.sgranjon.kotlinexampleproject.presentation.base.SingleLiveEvent
import com.sgranjon.kotlinexampleproject.presentation.base.viewmodel.BaseViewModel
import com.sgranjon.kotlinexampleproject.presentation.extensions.subscribeByIO
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(private val characterRepository: CharacterRepository) :
    BaseViewModel() {
    private val characterDetailLiveData = MutableLiveData<CharacterViewDataWrapper>()
    private val errorLiveEvent = SingleLiveEvent<Throwable>()
    fun retrieveCharacterDetail(id: Int) {
        characterRepository.retrieveCharacterById(id).subscribeByIO(
            onSuccess = {
                characterDetailLiveData.postValue(CharacterViewDataWrapper(it))
            },
            onError = {
                errorLiveEvent.postValue(it)
            }
        ).addToComposite()
    }

    fun getCharacterDetailLiveData(): LiveData<CharacterViewDataWrapper> = characterDetailLiveData
    fun getErrorLiveEvent(): LiveData<Throwable> = errorLiveEvent
}