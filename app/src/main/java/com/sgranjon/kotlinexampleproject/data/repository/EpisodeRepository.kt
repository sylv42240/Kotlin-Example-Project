package com.sgranjon.kotlinexampleproject.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.sgranjon.kotlinexampleproject.data.business.EpisodeBusinessHelper
import com.sgranjon.kotlinexampleproject.data.mapper.local.EpisodeEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.model.Episode
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Reusable
class EpisodeRepository @Inject constructor(
    private val episodeBusinessHelper: EpisodeBusinessHelper,
    private val episodeEntityDataMapper: EpisodeEntityDataMapper
) {
    fun retrieveEpisodeList(): Flow<PagingData<Episode>> =
        episodeBusinessHelper.retrieveEpisodeList().map {
            it.map { episode -> episodeEntityDataMapper.transformEntityToModel(episode) }
        }


    fun retrieveCharacterEpisodeList(id: Int): Flow<List<Episode>> =
        episodeBusinessHelper.retrieveCharacterEpisodeList(id).map {
            episodeEntityDataMapper.transformEntityList(it)
        }

}
