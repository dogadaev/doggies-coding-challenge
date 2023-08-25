package org.dogadaev.simplesurance_dogs_collection.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class NavigationActions(
    navController: NavHostController
) {
    val navigateToBreedsList: () -> Unit = {
        navController.navigate(Destinations.BreedsList) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToFavorites: () -> Unit = {
        navController.navigate(Destinations.Favorites) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}