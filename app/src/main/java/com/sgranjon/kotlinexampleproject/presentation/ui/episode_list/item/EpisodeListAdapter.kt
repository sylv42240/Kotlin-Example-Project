package com.sgranjon.kotlinexampleproject.presentation.ui.episode_list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.presentation.wrapper.EpisodeViewDataWrapper
import javax.inject.Inject


class EpisodeListAdapter @Inject constructor() :
    PagingDataAdapter<EpisodeViewDataWrapper, EpisodeViewHolder>(EpisodeComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_episode_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val EpisodeComparator = object : DiffUtil.ItemCallback<EpisodeViewDataWrapper>() {
            override fun areItemsTheSame(
                oldItem: EpisodeViewDataWrapper,
                newItem: EpisodeViewDataWrapper
            ): Boolean {
                return oldItem.getId() == newItem.getId()
            }

            override fun areContentsTheSame(
                oldItem: EpisodeViewDataWrapper,
                newItem: EpisodeViewDataWrapper
            ): Boolean {
                return oldItem.getId() == newItem.getId()
            }
        }
    }
}