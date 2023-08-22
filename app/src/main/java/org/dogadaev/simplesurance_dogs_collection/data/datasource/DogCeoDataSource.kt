package org.dogadaev.simplesurance_dogs_collection.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.expectSuccess
import io.ktor.client.request.get
import org.dogadaev.simplesurance_dogs_collection.data.model.BreedImages
import org.dogadaev.simplesurance_dogs_collection.data.model.Breeds

class DogCeoDataSource(
    private val client: HttpClient,
    private val baseUrl: String,
) {

    suspend fun fetchAllBreeds(): Breeds =
        client.get("${baseUrl}api/breeds/all") { expectSuccess = true }.body()

    suspend fun fetchBreedImages(
        breedName: String
    ): BreedImages =
        client.get("${baseUrl}api/breed/$breedName/images") { expectSuccess = true }.body()
}