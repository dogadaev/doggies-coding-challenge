package org.dogadaev.simplesurance_dogs_collection.data

import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.dogadaev.simplesurance_dogs_collection.application.AppGraph
import org.dogadaev.simplesurance_dogs_collection.data.datasource.DogCeoDataSource
import org.dogadaev.simplesurance_dogs_collection.data.datasource.impl.DogCeoDataSourceImpl
import org.dogadaev.simplesurance_dogs_collection.data.db.BreedsDataBase
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository
import org.dogadaev.simplesurance_dogs_collection.data.repository.impl.BreedsRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

object DataGraph {
    private val BASE_URL_KEY: Qualifier = StringQualifier("BaseUrlKey")

    val module = module {
        single(BASE_URL_KEY) { "https://dog.ceo/" }

        single {
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        }

        single {
            HttpClient {
                val buildType = get<AppGraph.BuildType>()

                if (buildType == AppGraph.BuildType.Debug) {
                    install(Logging) {
                        logger = Logger.ANDROID
                        level = LogLevel.ALL
                    }
                }
                install(ContentNegotiation) {
                    json(get())
                }
            }
        }

        single<DogCeoDataSource> {
            DogCeoDataSourceImpl(
                client = get(),
                baseUrl = get(BASE_URL_KEY)
            )
        }

        single<BreedsRepository> {
            BreedsRepositoryImpl(
                dogCeoDataSource = get(),
                breedsDao = get()
            )
        }

        single<BreedsDataBase> {
            Room.databaseBuilder(
                androidApplication().applicationContext,
                BreedsDataBase::class.java,
                "favorites.db"
            )
                .build()
        }

        single {
            get<BreedsDataBase>().favoritesDao()
        }
    }
}