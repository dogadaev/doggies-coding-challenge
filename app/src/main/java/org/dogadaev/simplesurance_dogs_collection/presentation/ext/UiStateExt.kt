package org.dogadaev.simplesurance_dogs_collection.presentation.ext

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import org.dogadaev.simplesurance_dogs_collection.presentation.UiState
import org.mobilenativefoundation.store.store5.StoreReadResponse

@OptIn(ExperimentalCoroutinesApi::class)
fun <T, R> Flow<UiState<T>>.mapSuccess(transform: suspend (T?) -> R) = mapLatest { state ->
    when (state) {
        is UiState.Data -> UiState.Data(transform(state.data))
        is UiState.Error -> state.copy()
        is UiState.Loading -> state
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<StoreReadResponse<T>>.toUiState() = mapLatest { it.toUiState() }

private fun <T> StoreReadResponse<T>.toUiState(): UiState<T> = when (this) {
    is StoreReadResponse.NoNewData,
    is StoreReadResponse.Data -> UiState.Data(dataOrNull())

    is StoreReadResponse.Error.Exception,
    is StoreReadResponse.Error.Message -> UiState.Error(errorMessageOrNull())

    is StoreReadResponse.Loading -> UiState.Loading
}