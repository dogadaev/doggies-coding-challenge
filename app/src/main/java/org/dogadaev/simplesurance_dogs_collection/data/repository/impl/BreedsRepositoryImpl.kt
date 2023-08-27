package org.dogadaev.simplesurance_dogs_collection.data.repository.impl

import kotlinx.coroutines.flow.map
import org.dogadaev.simplesurance_dogs_collection.data.datasource.DogCeoDataSource
import org.dogadaev.simplesurance_dogs_collection.data.db.BreedsDao
import org.dogadaev.simplesurance_dogs_collection.data.db.model.DbBreed
import org.dogadaev.simplesurance_dogs_collection.data.db.model.mapToUi
import org.dogadaev.simplesurance_dogs_collection.data.db.model.mapTpDb
import org.dogadaev.simplesurance_dogs_collection.data.model.ApiBreeds
import org.dogadaev.simplesurance_dogs_collection.data.model.mapToDb
import org.dogadaev.simplesurance_dogs_collection.data.model.mapToUi
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breed
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.BreedImage
import org.mobilenativefoundation.store.store5.Converter
import org.mobilenativefoundation.store.store5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.MutableStoreBuilder
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreWriteRequest
import org.mobilenativefoundation.store.store5.Updater
import org.mobilenativefoundation.store.store5.UpdaterResult

@OptIn(ExperimentalStoreApi::class)
internal class BreedsRepositoryImpl(
    private val dogCeoDataSource: DogCeoDataSource,
    private val breedsDao: BreedsDao,
) : BreedsRepository {

    private val breedsStore =
        MutableStoreBuilder.from<String, ApiBreeds, List<DbBreed>, List<Breed>>(
            fetcher = Fetcher.of { _ ->
                dogCeoDataSource
                    .fetchAllBreeds()
            },
            sourceOfTruth = SourceOfTruth.Companion.of(
                reader = { _ ->
                    breedsDao.getBreeds()
                        .map { list ->
                            if (list.isEmpty()) return@map null

                            list.mapToUi()
                                .sortedBy { it.name }
                        }
                },
                writer = { _, value ->
                    breedsDao.addBreeds(value)
                },
                delete = {
                    breedsDao.removeBreed(it)
                },
            ),
            converter = Converter.Builder<ApiBreeds, List<DbBreed>, List<Breed>>()
                .fromOutputToLocal {
                    it.map { model -> model.mapTpDb() }
                }
                .fromNetworkToLocal {
                    it
                        .mapToDb()
                        .sortedBy { item -> item.name }
                }
                .build()
        )
            .build(
                updater = Updater.by(
                    post = { _, value ->
                        UpdaterResult.Success.Typed(value)
                    }
                )
            )

    override fun getBreeds(
        fresh: Boolean
    ) = breedsStore.stream<List<Breed>>(
        request = StoreReadRequest.cached("", fresh)
    )

    override suspend fun updateFavoriteState(
        breed: Breed
    ) = breedsStore.write(
        request = StoreWriteRequest.of(breed.name, listOf(breed))
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