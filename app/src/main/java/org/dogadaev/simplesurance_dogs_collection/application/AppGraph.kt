package org.dogadaev.simplesurance_dogs_collection.application

import org.dogadaev.simplesurance_dogs_collection.BuildConfig
import org.koin.dsl.module

object AppGraph {
    enum class BuildType { Debug, Release }

    val module = module {
        single {
            @Suppress("KotlinConstantConditions")
            if (BuildConfig.BUILD_TYPE == "debug") BuildType.Debug else BuildType.Release
        }
    }
}