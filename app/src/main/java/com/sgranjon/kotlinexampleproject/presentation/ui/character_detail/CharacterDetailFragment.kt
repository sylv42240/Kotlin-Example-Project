package com.sgranjon.kotlinexampleproject.presentation.ui.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sgranjon.kotlinexampleproject.databinding.FragmentCharacterDetailBinding
import com.sgranjon.kotlinexampleproject.presentation.base.fragment.BaseVMFragment
import com.sgranjon.kotlinexampleproject.presentation.component.snackbar.SnackbarComponent
import com.sgranjon.kotlinexampleproject.presentation.extensions.observeSafe
import com.sgranjon.kotlinexampleproject.presentation.ui.character_detail.item.EpisodeListAdapter
import com.sgranjon.kotlinexampleproject.presentation.ui.main.MainActivity
import javax.inject.Inject

private const val BUNDLE_CHARACTER_DETAIL_ID = "bundleCharacterDetailId"

class CharacterDetailFragment :
    BaseVMFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding>() {
    override val viewModelClass = CharacterDetailViewModel::class

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterDetailBinding =
        FragmentCharacterDetailBinding::inflate

    @Inject
    lateinit var snackbarComponent: SnackbarComponent

    @Inject
    lateinit var episodeListAdapter: EpisodeListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(BUNDLE_CHARACTER_DETAIL_ID)?.let {
            viewModel.retrieveCharacterDetail(it)
            viewModel.retrieveCharacterEpisodeList(it)
        }
        observeCharacterDetail()
        setupRecyclerView()
    }

    private fun observeCharacterDetail() {
        viewModel.getCharacterDetailLiveData().observeSafe(viewLifecycleOwner) { character ->
            binding {
                Glide.with(requireContext())
                    .load(character.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(characterDetailImage)
                characterDetailName.text = character.getName()
                characterDetailGender.text = character.getGender(requireContext())
                characterDetailStatus.text = character.getStatus(requireContext())
                characterDetailSpecies.text = character.getSpecies()
                characterDetailOrigin.text = character.getOrigin()
                characterDetailEpisodeCount.text = character.getEpisodeCountText(requireContext())
                (requireActivity() as MainActivity).binding {
                    activityMainToolbar.title = character.getName()
                }
            }
        }
        viewModel.getEpisodeListLiveData().observeSafe(viewLifecycleOwner) { episodes ->
            episodes.map { println(it.getEpisodeNumber()) }
            episodeListAdapter.setItems(episodes)
        }
        viewModel.getErrorLiveEvent().observeSafe(viewLifecycleOwner) {
            snackbarComponent.displayError(requireContext(), it, requireView())
        }
    }

    private fun setupRecyclerView() {
        binding {
            characterDetailEpisodeListRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = RecyclerView.VERTICAL
                }
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
                adapter = episodeListAdapter
            }
        }
    }

}