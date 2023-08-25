package org.dogadaev.simplesurance_dogs_collection.ui.screens.breeds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    val state = viewModel.state.collectAsState(initial = UiState.Loading)

    when (val uiState = state.value) {
        is UiState.Data -> {
            uiState.data?.let { data ->
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp, vertical = 8.dp
                    ),
                ) {
                    val breeds = data.breeds.keys.toList()
                    items(
                        items = breeds,
                    ) { breedName ->
                        BreedsListItem(
                            breedName = breedName,
                            onCardClick = {
                                viewModel.click(
                                    BreedsViewModel.Actions.OpenDetails(breedName)
                                )
                            },
                            onFavoriteClick = {
                                viewModel.click(
                                    BreedsViewModel.Actions.AddToFavorite(
                                        breedName
                                    )
                                )
                            })
                    }
                }
            } ?: run {
                Text(
                    text = "Empty ;(",
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
    val selectedBreed = viewModel.selectedBreed.collectAsState()

    if (selectedBreed.value != null) {
        BreedDetailScreen(
            viewModel = get<BreedDetailsViewModel> {
                parametersOf(selectedBreed.value)
            },
            close = {
                viewModel.click(BreedsViewModel.Actions.CloseDetails)
            }
        )
    }
}