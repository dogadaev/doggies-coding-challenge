package org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.presentation.ext.mapToUi

@OptIn(ExperimentalCoroutinesApi::class)
class BreedsViewModel(
    breedsRepository: BreedsRepository
) : ViewModel() {

    private val refresh = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    val state = refresh
        .onStart { emit(false) }
        .flatMapLatest {
            breedsRepository
                .getBreeds(true)
        }
        .mapToUi()
        .distinctUntilChanged()


    private val _navigation = MutableStateFlow<String?>(null)

    val selectedBreed: StateFlow<String?> = _navigation

    fun click(action: Actions) {
        when (action) {
            is Actions.AddToFavorite -> {
                // todo
            }

            is Actions.OpenDetails -> {
                _navigation.value = action.breedName
            }

            Actions.CloseDetails -> _navigation.value = null
        }
    }

    fun reload() {
        refresh.tryEmit(true)
    }

    sealed class Actions {
        data class OpenDetails(val breedName: String) : Actions()
        data class AddToFavorite(val breedName: String) : Actions()
        object CloseDetails : Actions()
    }
}