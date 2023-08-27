package org.dogadaev.simplesurance_dogs_collection.presentation.usecase

import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.presentation.ext.toUiState

class AllBreedsUseCase(
    private val breedsRepository: BreedsRepository
) : BreedsUseCase(breedsRepository) {

    override fun getBreeds(
        fresh: Boolean
    ) = breedsRepository.getBreeds(fresh)
        .toUiState()
}