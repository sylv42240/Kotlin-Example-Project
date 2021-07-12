package com.sgranjon.kotlinexampleproject.presentation.ui.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sgranjon.kotlinexampleproject.data.repository.CharacterRepository
import com.sgranjon.kotlinexampleproject.presentation.base.SingleLiveEvent
import com.sgranjon.kotlinexampleproject.presentation.base.viewmodel.BaseViewModel
import com.sgranjon.kotlinexampleproject.presentation.extensions.subscribeByIO
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val characterRepository: CharacterRepository): BaseViewModel() {

    private val characterListLiveData = MutableLiveData<List<CharacterViewDataWrapper>>()
    private val errorLiveEvent = SingleLiveEvent<Throwable>()

    fun retrieveCharacterList(){
        characterRepository.retrieveCharacterList().subscribeByIO(
            onSuccess = { list ->
                characterListLiveData.postValue(list.map { CharacterViewDataWrapper(it) })
            },
            onError = {
                errorLiveEvent.postValue(it)
            }
        ).addToComposite()
    }

    fun getCharacterListLiveData(): LiveData<List<CharacterViewDataWrapper>> = characterListLiveData
    fun getErrorLiveEvent(): LiveData<Throwable> = errorLiveEvent

}