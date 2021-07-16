package com.sgranjon.kotlinexampleproject.presentation.ui.character.character_detail.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.presentation.wrapper.EpisodeViewDataWrapper
import javax.inject.Inject


class CharacterEpisodeListAdapter @Inject constructor() : RecyclerView.Adapter<CharacterEpisodeViewHolder>() {

    private val items = mutableListOf<EpisodeViewDataWrapper>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterEpisodeViewHolder {
        return CharacterEpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_character_episode_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterEpisodeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<EpisodeViewDataWrapper>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}