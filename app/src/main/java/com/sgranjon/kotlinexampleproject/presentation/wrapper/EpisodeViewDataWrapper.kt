package com.sgranjon.kotlinexampleproject.presentation.wrapper

import android.content.Context
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.data.model.Episode

class EpisodeViewDataWrapper(private val episode: Episode) {

    fun getId() = episode.id

    fun getName() = episode.name

    fun getEpisodeNumber() = episode.number

    fun getDate() = episode.date

    fun getCharacterCountText(context: Context) =
        context.getString(R.string.episode_character_count, episode.characterList.size)
}