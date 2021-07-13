package com.sgranjon.kotlinexampleproject.presentation.ui.character_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sgranjon.kotlinexampleproject.databinding.FragmentCharacterListBinding
import com.sgranjon.kotlinexampleproject.presentation.base.fragment.BaseVMFragment
import com.sgranjon.kotlinexampleproject.presentation.component.snackbar.SnackbarComponent
import com.sgranjon.kotlinexampleproject.presentation.extensions.hide
import com.sgranjon.kotlinexampleproject.presentation.extensions.observeSafe
import com.sgranjon.kotlinexampleproject.presentation.extensions.show
import com.sgranjon.kotlinexampleproject.presentation.ui.character_list.item.CharacterListAdapter
import javax.inject.Inject

class CharacterListFragment :
    BaseVMFragment<CharacterListViewModel, FragmentCharacterListBinding>() {

    override val viewModelClass = CharacterListViewModel::class

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterListBinding =
        FragmentCharacterListBinding::inflate

    @Inject
    lateinit var snackbarComponent: SnackbarComponent

    @Inject
    lateinit var characterListAdapter: CharacterListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.retrieveCharacterList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCharacterList()
        setupRecyclerView()
    }

    private fun observeCharacterList() {
        viewModel.getCharacterListLiveData().observeSafe(viewLifecycleOwner) { characterList ->
            characterListAdapter.setItems(characterList)
            binding {
                if (characterList.isEmpty()) {
                    characterListRecyclerView.hide()
                    characterListEmptyPlaceholderText.show()
                } else {
                    characterListRecyclerView.show()
                    characterListEmptyPlaceholderText.hide()
                }
            }
        }
        viewModel.getErrorLiveEvent().observeSafe(viewLifecycleOwner) {
            snackbarComponent.displayError(requireContext(), it, requireView())
        }
    }

    private fun setupRecyclerView() {
        binding {
            characterListRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = RecyclerView.VERTICAL
                }
                adapter = characterListAdapter
            }
        }
        characterListAdapter.onItemClicked = ::onCharacterClicked
    }

    private fun onCharacterClicked(id: Int) {
        // TODO : Implement navigation
        Toast.makeText(requireContext(), id.toString(), Toast.LENGTH_SHORT).show()
    }

}