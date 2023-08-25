package org.dogadaev.simplesurance_dogs_collection.data.datasource.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.expectSuccess
import io.ktor.client.request.get
import org.dogadaev.simplesurance_dogs_collection.data.datasource.DogCeoDataSource
import org.dogadaev.simplesurance_dogs_collection.data.model.ApiBreedImage
import org.dogadaev.simplesurance_dogs_collection.data.model.ApiBreeds

internal class DogCeoDataSourceImpl(
    private val client: HttpClient,
    private val baseUrl: String,
): DogCeoDataSource {

    override suspend fun fetchAllBreeds(): ApiBreeds =
        client.get("${baseUrl}api/breeds/list/all") { expectSuccess = true }.body()

    override suspend fun fetchBreedImage(
        breedName: String
    ): ApiBreedImage =
        client.get("${baseUrl}api/breed/$breedName/images/random") { expectSuccess = true }.body()
}