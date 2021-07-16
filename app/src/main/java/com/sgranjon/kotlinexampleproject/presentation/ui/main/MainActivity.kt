package com.sgranjon.kotlinexampleproject.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.databinding.ActivityMainBinding
import com.sgranjon.kotlinexampleproject.presentation.base.activity.BaseVMActivity
import com.sgranjon.kotlinexampleproject.presentation.component.dialog.DialogComponent
import com.sgranjon.kotlinexampleproject.presentation.component.snackbar.SnackbarComponent
import com.sgranjon.kotlinexampleproject.presentation.extensions.observeSafe
import javax.inject.Inject

class MainActivity : BaseVMActivity<MainViewModel, ActivityMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override val viewModelClass = MainViewModel::class

    @Inject
    lateinit var dialogComponent: DialogComponent

    @Inject
    lateinit var snackbarComponent: SnackbarComponent

    private val navController by lazy { findNavController(getNavControllerId()) }

    private var currentTheme: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.retrieveCurrentTheme()
        setupNavigation()
        observeTheme()
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
            activityMainBottomNavigationBar.setupWithNavController(navController)
            activityMainToolbar.setupWithNavController(navController, appBarConfiguration)
            activityMainToolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.themeUpdate) {
                    displayThemeUpdateDialog()
                }
                true
            }
        }
    }

    private fun observeTheme() {
        viewModel.getThemeLiveEvent().observeSafe(this) { theme ->
            this.currentTheme = theme
            AppCompatDelegate.setDefaultNightMode(theme)
        }
        viewModel.getErrorLiveEvent().observeSafe(this) {
            snackbarComponent.displayError(this, it, binding.activityMainRootView)
        }
    }

    private fun displayThemeUpdateDialog() {
        dialogComponent.displayTextDialog(
            context = this,
            title = R.string.dialog_update_theme_title,
            content = R.string.dialog_update_theme_message,
            isCancelable = true,
            onPositiveClick = {
                if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) {
                    viewModel.updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    viewModel.updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        )
    }
}