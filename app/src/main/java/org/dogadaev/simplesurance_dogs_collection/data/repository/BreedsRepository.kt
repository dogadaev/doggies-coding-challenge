package org.dogadaev.simplesurance_dogs_collection.data.repository

import kotlinx.coroutines.flow.Flow
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.BreedImage
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breed
import org.mobilenativefoundation.store.store5.StoreReadResponse
import org.mobilenativefoundation.store.store5.StoreWriteResponse

interface BreedsRepository {

    fun getBreeds(fresh: Boolean): Flow<StoreReadResponse<List<Breed>>>

    fun getBreedImage(
        breedName: String,
        fresh: Boolean = false
    ): Flow<StoreReadResponse<BreedImage>>

    suspend fun updateFavoriteState(breed: Breed): StoreWriteResponse
}