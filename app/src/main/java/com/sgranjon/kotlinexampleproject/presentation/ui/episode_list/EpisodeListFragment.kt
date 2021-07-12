package com.sgranjon.kotlinexampleproject.presentation.ui.episode_list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgranjon.kotlinexampleproject.databinding.FragmentEpisodeListBinding
import com.sgranjon.kotlinexampleproject.presentation.base.fragment.BaseVMFragment

class EpisodeListFragment : BaseVMFragment<EpisodeListViewModel, FragmentEpisodeListBinding>() {

    override val viewModelClass = EpisodeListViewModel::class

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEpisodeListBinding =
        FragmentEpisodeListBinding::inflate

}