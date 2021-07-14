package com.sgranjon.kotlinexampleproject.presentation.ui.character_detail.item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.presentation.wrapper.EpisodeViewDataWrapper

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        episode: EpisodeViewDataWrapper,
    ) {
        with(itemView) {
            val name = findViewById<TextView>(R.id.view_episode_item_name)
            val number = findViewById<TextView>(R.id.view_episode_item_number)

            name.text = episode.getName()
            number.text = episode.getEpisodeNumber()
        }
    }
}