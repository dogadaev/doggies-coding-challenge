package org.dogadaev.simplesurance_dogs_collection.data.datasource

import org.dogadaev.simplesurance_dogs_collection.data.model.ApiBreedImage
import org.dogadaev.simplesurance_dogs_collection.data.model.ApiBreeds

internal interface DogCeoDataSource {

    suspend fun fetchAllBreeds(): ApiBreeds

    suspend fun fetchBreedImage(
        breedName: String
    ): ApiBreedImage
}