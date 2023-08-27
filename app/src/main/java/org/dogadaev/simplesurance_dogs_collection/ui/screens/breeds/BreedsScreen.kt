package org.dogadaev.simplesurance_dogs_collection.ui.screens.breeds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.dogadaev.simplesurance_dogs_collection.presentation.UiState
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.BreedDetailsViewModel
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.BreedsViewModel
import org.dogadaev.simplesurance_dogs_collection.ui.component.RetryErrorMessage
import org.dogadaev.simplesurance_dogs_collection.ui.screens.details.BreedDetailScreen
import org.koin.androidx.compose.get
import org.koin.core.parameter.parametersOf

@Composable
fun BreedsScreen(
    viewModel: BreedsViewModel,
) {
    when (val uiState = viewModel.state.collectAsState(initial = UiState.Loading).value) {
        is UiState.Data -> {
            uiState.data?.let { items ->
                BreedsList(
                    items = items,
                    onCardClick = {
                        viewModel.click(
                            BreedsViewModel.Actions.OpenDetails(it)
                        )
                    },
                    onFavoriteClick = {
                        viewModel.click(
                            BreedsViewModel.Actions.AddToFavorite(it)
                        )
                    },
                )
            } ?: run {
                Text(
                    text = "Empty ;(",
                )
            }

            val selectedBreed = viewModel.selectedBreed.collectAsState().value

            if (selectedBreed != null) {
                BreedDetailScreen(
                    viewModel = get<BreedDetailsViewModel> {
                        parametersOf(selectedBreed)
                    },
                    close = {
                        viewModel.click(BreedsViewModel.Actions.CloseDetails)
                    }
                )
            }
        }

        is UiState.Error -> {
            RetryErrorMessage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                errorMessage = "Oops, something went wrong ;(\n${uiState.message ?: "Unknown Error!"}",
                retryButtonTitle = "RETRY",
                contentDescription = "Try again.",
                retry = {
                    viewModel.reload()
                }
            )
        }

        UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }
    }
}