package com.sgranjon.kotlinexampleproject.presentation.wrapper

import com.sgranjon.kotlinexampleproject.data.model.Episode

class EpisodeViewDataWrapper(private val episode: Episode) {
    fun getName() = episode.name

    fun getEpisodeNumber() = episode.number
}