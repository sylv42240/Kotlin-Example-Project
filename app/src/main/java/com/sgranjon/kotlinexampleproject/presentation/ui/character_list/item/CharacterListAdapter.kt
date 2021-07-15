package com.sgranjon.kotlinexampleproject.presentation.ui.character_list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.presentation.wrapper.CharacterViewDataWrapper
import javax.inject.Inject


class CharacterListAdapter @Inject constructor() :
    PagingDataAdapter<CharacterViewDataWrapper, CharacterViewHolder>(CharacterComparator) {


    var onItemClicked: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_character_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onItemClicked) }
    }
    
    companion object {
        private val CharacterComparator = object : DiffUtil.ItemCallback<CharacterViewDataWrapper>() {
            override fun areItemsTheSame(
                oldItem: CharacterViewDataWrapper,
                newItem: CharacterViewDataWrapper
            ): Boolean {
                return oldItem.getId() == newItem.getId()
            }

            override fun areContentsTheSame(
                oldItem: CharacterViewDataWrapper,
                newItem: CharacterViewDataWrapper
            ): Boolean {
                return oldItem.getId() == newItem.getId()
            }
        }
    }
}