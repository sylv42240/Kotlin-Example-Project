package com.sgranjon.kotlinexampleproject.presentation.ui.location_list

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
import com.sgranjon.kotlinexampleproject.databinding.FragmentLocationListBinding
import com.sgranjon.kotlinexampleproject.presentation.base.fragment.BaseVMFragment
import com.sgranjon.kotlinexampleproject.presentation.component.snackbar.SnackbarComponent
import com.sgranjon.kotlinexampleproject.presentation.extensions.hide
import com.sgranjon.kotlinexampleproject.presentation.extensions.observeSafe
import com.sgranjon.kotlinexampleproject.presentation.extensions.show
import com.sgranjon.kotlinexampleproject.presentation.ui.location_list.item.LocationListAdapter
import javax.inject.Inject

class LocationListFragment : BaseVMFragment<LocationListViewModel, FragmentLocationListBinding>() {

    override val viewModelClass = LocationListViewModel::class

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLocationListBinding =
        FragmentLocationListBinding::inflate

    @Inject
    lateinit var snackbarComponent: SnackbarComponent

    @Inject
    lateinit var locationListAdapter: LocationListAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.retrieveLocationList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEpisodeList()
        setupRecyclerView()
        setupRefreshLayout()
    }

    private fun observeEpisodeList() {
        viewModel.getLocationPagingDataLiveData()
            .observeSafe(viewLifecycleOwner) { episodeList ->
                locationListAdapter.submitData(lifecycle, episodeList)
                binding {
                    locationListSwipeLayout.isRefreshing = false
                }
            }
    }

    private fun setupRecyclerView() {
        binding {
            locationListRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = RecyclerView.VERTICAL
                }
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
                adapter = locationListAdapter
            }
        }
        locationListAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.Error) {
                snackbarComponent.displayError(
                    requireContext(),
                    RequestFailException(),
                    requireView()
                )
                binding {
                    locationListRecyclerView.hide()
                    locationListEmptyPlaceholderText.show()
                }
            } else {
                binding {
                    locationListRecyclerView.show()
                    locationListEmptyPlaceholderText.hide()
                }
            }
        }
    }

    private fun setupRefreshLayout() {
        binding.locationListSwipeLayout.setOnRefreshListener {
            viewModel.retrieveLocationList()
        }
    }
}