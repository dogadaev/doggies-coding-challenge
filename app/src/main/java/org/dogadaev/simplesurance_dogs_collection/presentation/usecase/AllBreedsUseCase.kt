package org.dogadaev.simplesurance_dogs_collection.presentation.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breed
import org.dogadaev.simplesurance_dogs_collection.presentation.UiState
import org.dogadaev.simplesurance_dogs_collection.presentation.ext.mapSuccess
import org.dogadaev.simplesurance_dogs_collection.presentation.ext.toUiState
import org.mobilenativefoundation.store.store5.StoreReadResponse

class AllBreedsUseCase(
    private val breedsRepository: BreedsRepository
) : BreedsUseCase(breedsRepository) {

    override fun getBreeds(
        fresh: Boolean
    ) = breedsRepository.getBreeds(fresh)
        .toUiState()
}