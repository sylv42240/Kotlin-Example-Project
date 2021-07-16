package com.sgranjon.kotlinexampleproject.presentation.di.module

import androidx.lifecycle.ViewModel
import com.sgranjon.kotlinexampleproject.presentation.di.annotation.ViewModelKey
import com.sgranjon.kotlinexampleproject.presentation.ui.character.character_detail.CharacterDetailViewModel
import com.sgranjon.kotlinexampleproject.presentation.ui.character.character_list.CharacterListViewModel
import com.sgranjon.kotlinexampleproject.presentation.ui.episode_list.EpisodeListViewModel
import com.sgranjon.kotlinexampleproject.presentation.ui.location_list.LocationListViewModel
import com.sgranjon.kotlinexampleproject.presentation.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterListViewModel::class)
    abstract fun bindCharacterListViewModel(viewModel: CharacterListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeListViewModel::class)
    abstract fun bindEpisodeListViewModel(viewModel: EpisodeListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationListViewModel::class)
    abstract fun bindLocationListViewModel(viewModel: LocationListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindCharacterDetailViewModel(viewModel: CharacterDetailViewModel): ViewModel
}