package org.dogadaev.simplesurance_dogs_collection.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.BreedsViewModel
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.FavoriteBreedsViewModel
import org.dogadaev.simplesurance_dogs_collection.ui.screens.breeds.BreedsScreen
import org.dogadaev.simplesurance_dogs_collection.ui.screens.favorites.FavoriteBreedsScreen
import org.koin.androidx.compose.get

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
            val breedsListViewModel: BreedsViewModel = get()

            BreedsScreen(
                viewModel = breedsListViewModel,
            )
        }

        composable(
            route = Destinations.Favorites
        ) {
            val favoriteBreedsViewModel: FavoriteBreedsViewModel = get()

            FavoriteBreedsScreen(
                viewModel = favoriteBreedsViewModel
            )
        }
    }
}