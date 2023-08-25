package org.dogadaev.simplesurance_dogs_collection.data.repository

import kotlinx.coroutines.flow.Flow
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.BreedImage
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breeds
import org.mobilenativefoundation.store.store5.StoreReadResponse

interface BreedsRepository {

    fun getBreeds(fresh: Boolean): Flow<StoreReadResponse<Breeds>>
    fun getBreedImage(
        breedName: String,
        fresh: Boolean = false
    ): Flow<StoreReadResponse<BreedImage>>
}