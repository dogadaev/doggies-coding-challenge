package org.dogadaev.simplesurance_dogs_collection.ui.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.dogadaev.simplesurance_dogs_collection.presentation.UiState
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.FavoriteBreedsViewModel

@Composable
fun FavoriteBreedsScreen(
    viewModel: FavoriteBreedsViewModel
) {
    val state = viewModel.state.collectAsState(initial = UiState.Loading)

}