package org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.presentation.ext.toUiState

@OptIn(ExperimentalCoroutinesApi::class)
class BreedDetailsViewModel(
    val breedName: String,
    breedsRepository: BreedsRepository
) : ViewModel() {

    private val refresh = MutableSharedFlow<Boolean>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    val state = refresh
        .onStart { emit(false) }
        .flatMapLatest {
            breedsRepository.getBreedImage(
                breedName = breedName,
                fresh = it
            )
        }
        .toUiState()
        .distinctUntilChanged()

    fun reload() {
        refresh.tryEmit(true)
    }
}