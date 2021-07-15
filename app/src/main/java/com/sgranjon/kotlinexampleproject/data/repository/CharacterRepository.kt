package com.sgranjon.kotlinexampleproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava2.flowable
import com.sgranjon.kotlinexampleproject.data.business.CharacterBusinessHelper
import com.sgranjon.kotlinexampleproject.data.business.pager.CharacterPagingSource
import com.sgranjon.kotlinexampleproject.data.mapper.local.CharacterEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.mapper.local.EpisodeEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.model.Character
import com.sgranjon.kotlinexampleproject.data.model.Episode
import dagger.Reusable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class CharacterRepository @Inject constructor(
    private val characterBusinessHelper: CharacterBusinessHelper,
    private val characterEntityDataMapper: CharacterEntityDataMapper,
    private val episodeEntityDataMapper: EpisodeEntityDataMapper,
    private val characterPagingSource: CharacterPagingSource
) {
    fun retrieveCharacterList(): Observable<PagingData<Character>> = Observable.defer {
        characterBusinessHelper.retrieveCharacterList().map {
            it.map { character -> characterEntityDataMapper.transformEntityToModel(character) }
        }
    }

    fun retrieveCharacterById(id: Int): Single<Character> = Single.defer {
        Single.just(
            characterEntityDataMapper.transformEntityToModel(
                characterBusinessHelper.retrieveCharacterById(
                    id
                )
            )
        )
    }

    fun retrieveCharacterEpisodeList(id: Int): Single<List<Episode>> = Single.defer {
        characterBusinessHelper.retrieveCharacterEpisodeList(id).map {
            episodeEntityDataMapper.transformEntityList(it)
        }
    }
}
