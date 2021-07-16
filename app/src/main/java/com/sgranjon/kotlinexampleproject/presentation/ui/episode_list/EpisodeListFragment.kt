package com.sgranjon.kotlinexampleproject.presentation.ui.episode_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sgranjon.kotlinexampleproject.data.exception.RequestFailException
import com.sgranjon.kotlinexampleproject.databinding.FragmentEpisodeListBinding
import com.sgranjon.kotlinexampleproject.presentation.base.fragment.BaseVMFragment
import com.sgranjon.kotlinexampleproject.presentation.component.snackbar.SnackbarComponent
import com.sgranjon.kotlinexampleproject.presentation.extensions.hide
import com.sgranjon.kotlinexampleproject.presentation.extensions.observeSafe
import com.sgranjon.kotlinexampleproject.presentation.extensions.show
import com.sgranjon.kotlinexampleproject.presentation.ui.episode_list.item.EpisodeListAdapter
import javax.inject.Inject

class EpisodeListFragment : BaseVMFragment<EpisodeListViewModel, FragmentEpisodeListBinding>() {

    override val viewModelClass = EpisodeListViewModel::class

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEpisodeListBinding =
        FragmentEpisodeListBinding::inflate

    @Inject
    lateinit var snackbarComponent: SnackbarComponent

    @Inject
    lateinit var episodeListAdapter: EpisodeListAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.retrieveEpisodeList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEpisodeList()
        setupRecyclerView()
        setupRefreshLayout()
    }

    private fun observeEpisodeList() {
        viewModel.getEpisodePagingDataLiveData()
            .observeSafe(viewLifecycleOwner) { episodeList ->
                episodeListAdapter.submitData(lifecycle, episodeList)
                binding {
                    episodeListSwipeLayout.isRefreshing = false
                }
            }
    }

    private fun setupRecyclerView() {
        binding {
            episodeListRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = RecyclerView.VERTICAL
                }
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
                adapter = episodeListAdapter
            }
        }
        episodeListAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.Error) {
                snackbarComponent.displayError(
                    requireContext(),
                    RequestFailException(),
                    requireView()
                )
                binding {
                    episodeListRecyclerView.hide()
                    episodeListEmptyPlaceholderText.show()
                }
            } else {
                binding {
                    episodeListRecyclerView.show()
                    episodeListEmptyPlaceholderText.hide()
                }
            }
        }
    }

    private fun setupRefreshLayout() {
        binding.episodeListSwipeLayout.setOnRefreshListener {
            viewModel.retrieveEpisodeList()
        }
    }
}