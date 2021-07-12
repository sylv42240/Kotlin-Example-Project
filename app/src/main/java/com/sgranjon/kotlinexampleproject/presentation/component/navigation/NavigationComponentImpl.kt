package com.sgranjon.kotlinexampleproject.presentation.component.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class NavigationComponentImpl @Inject constructor() : NavigationComponent {

    private var lastNavigationEventTimestamp: Long = 0

    override fun isNavigationEventBlocked(
        navController: NavController,
        expectedDestinationId: Int?
    ): Boolean {
        return if (System.currentTimeMillis() - lastNavigationEventTimestamp < NavigationValues.NAVIGATION_EVENT_DELAY
            || expectedDestinationId != null && navController.currentDestination?.id != expectedDestinationId
        ) {
            true
        } else {
            lastNavigationEventTimestamp = System.currentTimeMillis()
            false
        }
    }


}