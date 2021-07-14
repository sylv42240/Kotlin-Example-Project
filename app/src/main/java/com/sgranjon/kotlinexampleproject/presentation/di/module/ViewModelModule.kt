package com.sgranjon.kotlinexampleproject.presentation.di.module

import androidx.lifecycle.ViewModel
import com.sgranjon.kotlinexampleproject.presentation.di.annotation.ViewModelKey
import com.sgranjon.kotlinexampleproject.presentation.ui.character_detail.CharacterDetailViewModel
import com.sgranjon.kotlinexampleproject.presentation.ui.character_list.CharacterListViewModel
import com.sgranjon.kotlinexampleproject.presentation.ui.episode_list.EpisodeListViewModel
import com.sgranjon.kotlinexampleproject.presentation.ui.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

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
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindCharacterDetailViewModel(viewModel: CharacterDetailViewModel): ViewModel
}