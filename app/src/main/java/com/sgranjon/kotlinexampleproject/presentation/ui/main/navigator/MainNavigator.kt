package com.sgranjon.kotlinexampleproject.presentation.ui.main.navigator

import androidx.navigation.NavController
import com.sgranjon.kotlinexampleproject.presentation.component.navigation.NavigationComponent
import com.sgranjon.kotlinexampleproject.presentation.ui.character_list.CharacterListFragmentDirections
import dagger.Lazy
import javax.inject.Inject

class MainNavigator @Inject constructor(
    private val navController: Lazy<NavController>,
    private val navigationComponent: NavigationComponent
) : CharacterListNavigatorListener {

    override fun displayCharacterDetail(id: Int) {
        if (navigationComponent.isNavigationEventBlocked(navController.get())) {
            return
        } else {
            navController.get()
                .navigate(
                    CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                        id
                    )
                )
        }
    }

}