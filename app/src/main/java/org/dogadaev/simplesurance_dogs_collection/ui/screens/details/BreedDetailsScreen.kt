package org.dogadaev.simplesurance_dogs_collection.ui.screens.details

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import org.dogadaev.simplesurance_dogs_collection.R
import org.dogadaev.simplesurance_dogs_collection.presentation.UiState
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.BreedDetailsViewModel
import org.dogadaev.simplesurance_dogs_collection.ui.component.RetryErrorMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailScreen(
    viewModel: BreedDetailsViewModel,
    close: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = close,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = viewModel.breedName.uppercase(),
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            when (val uiState = viewModel.state.collectAsState(initial = UiState.Loading).value) {
                is UiState.Data -> {
                    val context = LocalContext.current
                    val imageLoader = remember {
                        buildImageRequest(
                            imageUrl = uiState.data?.image,
                            context = context,
                        )
                    }

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        model = imageLoader,
                        contentDescription = null
                    )
                }

                is UiState.Error -> {
                    RetryErrorMessage(
                        modifier = Modifier
                            .padding(top = 32.dp),
                        errorMessage = "Oops, something went wrong ;(",
                        retryButtonTitle = "RETRY",
                        contentDescription = "Try again.",
                        retry = {
                            viewModel.reload()
                        }
                    )
                }

                is UiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

private fun buildImageRequest(
    imageUrl: String?,
    context: Context
) = ImageRequest.Builder(context)
    .data(imageUrl)
    .crossfade(true)
    .fallback(R.drawable.ic_launcher_foreground)
    .networkCachePolicy(CachePolicy.ENABLED)
    .diskCachePolicy(CachePolicy.ENABLED)
    .memoryCachePolicy(CachePolicy.ENABLED)
    .build()