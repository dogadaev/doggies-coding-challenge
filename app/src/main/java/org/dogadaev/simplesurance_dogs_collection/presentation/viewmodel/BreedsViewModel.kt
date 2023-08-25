package org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breed
import org.dogadaev.simplesurance_dogs_collection.presentation.ext.toUiState
import org.dogadaev.simplesurance_dogs_collection.presentation.usecase.AllBreedsUseCase
import org.dogadaev.simplesurance_dogs_collection.presentation.usecase.BreedsUseCase
import org.dogadaev.simplesurance_dogs_collection.presentation.usecase.FavoriteBreedsUseCase

@OptIn(ExperimentalCoroutinesApi::class)
sealed class BreedsViewModel(
    private val breedsUseCase: BreedsUseCase,
) : ViewModel() {

    private val refresh = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    val state = refresh
        .onStart { emit(false) }
        .flatMapLatest {
            breedsUseCase
                .getBreeds(it)
        }
        .distinctUntilChanged()

    private val _navigation = MutableStateFlow<String?>(null)

    val selectedBreed: StateFlow<String?> = _navigation

    fun click(action: Actions) {
        when (action) {
            is Actions.AddToFavorite -> {
                viewModelScope.launch {
                    breedsUseCase.updateFavoriteState(
                        action.breed.copy(
                            isFavorite = action.breed.isFavorite.not()
                        )
                    )
                }
            }

            is Actions.OpenDetails -> {
                _navigation.value = action.breed.name
            }

            Actions.CloseDetails -> _navigation.value = null
        }
    }

    fun reload() {
        refresh.tryEmit(true)
    }

    sealed class Actions {
        data class OpenDetails(val breed: Breed) : Actions()
        data class AddToFavorite(val breed: Breed) : Actions()
        object CloseDetails : Actions()
    }
}

class AllBreedsViewModel(
    useCase: AllBreedsUseCase
) : BreedsViewModel(useCase)

class FavoriteBreedsViewModel(
    useCase: FavoriteBreedsUseCase
) : BreedsViewModel(useCase)