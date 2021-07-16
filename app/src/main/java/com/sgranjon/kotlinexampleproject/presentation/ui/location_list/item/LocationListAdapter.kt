package com.sgranjon.kotlinexampleproject.presentation.ui.location_list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.presentation.wrapper.LocationViewDataWrapper
import javax.inject.Inject


class LocationListAdapter @Inject constructor() :
    PagingDataAdapter<LocationViewDataWrapper, LocationViewHolder>(LocationComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_location_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val LocationComparator = object : DiffUtil.ItemCallback<LocationViewDataWrapper>() {
            override fun areItemsTheSame(
                oldItem: LocationViewDataWrapper,
                newItem: LocationViewDataWrapper
            ): Boolean {
                return oldItem.getId() == newItem.getId()
            }

            override fun areContentsTheSame(
                oldItem: LocationViewDataWrapper,
                newItem: LocationViewDataWrapper
            ): Boolean {
                return oldItem.getId() == newItem.getId()
            }
        }
    }
}