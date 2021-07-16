package com.sgranjon.kotlinexampleproject.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.databinding.ActivityMainBinding
import com.sgranjon.kotlinexampleproject.presentation.base.activity.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    private val navController by lazy { findNavController(getNavControllerId()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    fun getNavControllerId() = R.id.activity_main_container

    private fun setupNavigation() {
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.characterListFragment,
                R.id.episodeListFragment,
                R.id.locationListFragment
            ),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        binding {
            activityMainToolbar.setupWithNavController(navController, appBarConfiguration)
            activityMainBottomNavigationBar.setupWithNavController(navController)
        }
    }
}