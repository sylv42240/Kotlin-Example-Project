package com.sgranjon.kotlinexampleproject.presentation.ui.location_list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgranjon.kotlinexampleproject.databinding.FragmentLocationListBinding
import com.sgranjon.kotlinexampleproject.presentation.base.fragment.BaseVMFragment

class LocationListFragment : BaseVMFragment<LocationListViewModel, FragmentLocationListBinding>() {

    override val viewModelClass = LocationListViewModel::class

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLocationListBinding =
        FragmentLocationListBinding::inflate

}