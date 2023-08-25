package org.dogadaev.simplesurance_dogs_collection.presentation.usecase

import kotlinx.coroutines.flow.Flow
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breed
import org.dogadaev.simplesurance_dogs_collection.presentation.UiState

abstract class BreedsUseCase(
    private val breedsRepository: BreedsRepository
) {
    abstract fun getBreeds(
        fresh: Boolean
    ): Flow<UiState<List<Breed>>>

    suspend fun updateFavoriteState(
        breed: Breed
    ) = breedsRepository.updateFavoriteState(breed)
}