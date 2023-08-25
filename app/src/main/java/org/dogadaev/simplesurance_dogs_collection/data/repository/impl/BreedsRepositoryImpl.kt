package org.dogadaev.simplesurance_dogs_collection.data.repository.impl

import org.dogadaev.simplesurance_dogs_collection.data.datasource.DogCeoDataSource
import org.dogadaev.simplesurance_dogs_collection.data.model.mapToUi
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.BreedImage
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.StoreReadRequest
import kotlin.time.Duration.Companion.minutes

internal class BreedsRepositoryImpl(
    private val dogCeoDataSource: DogCeoDataSource,
) : BreedsRepository {

    private val breedsStore = StoreBuilder.from(
        fetcher = Fetcher.of {
            dogCeoDataSource
                .fetchAllBreeds()
                .mapToUi()
        },
    )
        .build()

    override fun getBreeds(
        fresh: Boolean
    ) = breedsStore.stream(
        request = StoreReadRequest.cached(Unit, fresh)
    )

    private val imagesStore = StoreBuilder.from<String, BreedImage>(
        fetcher = Fetcher.of { breedName ->
            dogCeoDataSource
                .fetchBreedImage(breedName)
                .mapToUi(
                    breedName = breedName
                )

        },
    )
        .cachePolicy(
            memoryPolicy = MemoryPolicy.builder<String, BreedImage>()
                .build()
        )
        .build()

    override fun getBreedImage(
        breedName: String,
        fresh: Boolean
    ) = imagesStore.stream(
        request = StoreReadRequest.cached(breedName, fresh)
    )
}