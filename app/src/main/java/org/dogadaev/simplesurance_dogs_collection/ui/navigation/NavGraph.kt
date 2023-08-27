package org.dogadaev.simplesurance_dogs_collection.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.AllBreedsViewModel
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.FavoriteBreedsViewModel
import org.dogadaev.simplesurance_dogs_collection.ui.screens.breeds.BreedsScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.BreedsList,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(
            route = Destinations.BreedsList
        ) {
            BreedsScreen(
                viewModel = koinViewModel<AllBreedsViewModel>(),
            )
        }

        composable(
            route = Destinations.Favorites
        ) {
            BreedsScreen(
                viewModel = koinViewModel<FavoriteBreedsViewModel>(),
            )
        }
    }
}