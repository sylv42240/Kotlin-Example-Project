package com.sgranjon.kotlinexampleproject.presentation.di.module.activity

import androidx.navigation.findNavController
import com.sgranjon.kotlinexampleproject.presentation.di.annotation.PerActivity
import com.sgranjon.kotlinexampleproject.presentation.di.annotation.PerFragment
import com.sgranjon.kotlinexampleproject.presentation.ui.character_list.CharacterListFragment
import com.sgranjon.kotlinexampleproject.presentation.ui.episode_list.EpisodeListFragment
import com.sgranjon.kotlinexampleproject.presentation.ui.main.MainActivity
import com.sgranjon.kotlinexampleproject.presentation.ui.settings.SettingsFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


@Module(includes = [CharacterFragmentModule::class, EpisodeFragmentModule::class, SettingsFragmentModule::class])
class MainActivityModule {

    @PerActivity
    @Provides
    fun mainNavController(mainActivity: MainActivity) =
        mainActivity.findNavController(mainActivity.getNavControllerId())
}

@Module
private abstract class CharacterFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun characterListFragmentInjector(): CharacterListFragment

}

@Module
private abstract class EpisodeFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun episodeListFragmentInjector(): EpisodeListFragment

}

@Module
private abstract class SettingsFragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun settingsFragmentInjector(): SettingsFragment

}