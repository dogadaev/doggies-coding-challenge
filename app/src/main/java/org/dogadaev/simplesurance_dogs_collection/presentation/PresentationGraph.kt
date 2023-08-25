package org.dogadaev.simplesurance_dogs_collection.presentation

import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.BreedDetailsViewModel
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.BreedsViewModel
import org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel.FavoriteBreedsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationGraph {
    val module = module {

        viewModel {
            BreedsViewModel(
                breedsRepository = get()
            )
        }

        viewModel { params ->
            BreedDetailsViewModel(
                breedName = params.get(),
                breedsRepository = get()
            )
        }

        viewModel {
            FavoriteBreedsViewModel(
                breedsRepository = get()
            )
        }
    }
}