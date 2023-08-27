package org.dogadaev.simplesurance_dogs_collection.presentation

import org.dogadaev.simplesurance_dogs_collection.presentation.usecase.AllBreedsUseCase
import org.dogadaev.simplesurance_dogs_collection.presentation.usecase.FavoriteBreedsUseCase
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.AllBreedsViewModel
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.BreedDetailsViewModel
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.FavoriteBreedsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationGraph {
    val module = module {

        viewModel { params ->
            BreedDetailsViewModel(
                breedName = params.get(),
                breedsRepository = get()
            )
        }

        viewModel {
            AllBreedsViewModel(
                useCase = AllBreedsUseCase(
                    breedsRepository = get()
                ),
            )
        }

        viewModel {
            FavoriteBreedsViewModel(
                useCase = FavoriteBreedsUseCase(
                    breedsRepository = get()
                ),
            )
        }
    }
}