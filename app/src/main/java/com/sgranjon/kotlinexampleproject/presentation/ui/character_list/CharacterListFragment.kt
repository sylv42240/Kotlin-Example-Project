package com.sgranjon.kotlinexampleproject.presentation.ui.character_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgranjon.kotlinexampleproject.databinding.FragmentCharacterListBinding
import com.sgranjon.kotlinexampleproject.presentation.base.fragment.BaseVMFragment
import com.sgranjon.kotlinexampleproject.presentation.component.snackbar.SnackbarComponent
import com.sgranjon.kotlinexampleproject.presentation.extensions.observeSafe
import javax.inject.Inject

class CharacterListFragment :
    BaseVMFragment<CharacterListViewModel, FragmentCharacterListBinding>() {

    override val viewModelClass = CharacterListViewModel::class

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterListBinding =
        FragmentCharacterListBinding::inflate

    @Inject
    lateinit var snackbarComponent: SnackbarComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.retrieveCharacterList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCharacterList()
    }

    private fun observeCharacterList() {
        viewModel.getCharacterListLiveData().observeSafe(viewLifecycleOwner) { characterList ->
            println(characterList.map { it.getName() })
        }
        viewModel.getErrorLiveEvent().observeSafe(viewLifecycleOwner) {
            snackbarComponent.displayError(requireContext(), it, requireView())
        }
    }

}