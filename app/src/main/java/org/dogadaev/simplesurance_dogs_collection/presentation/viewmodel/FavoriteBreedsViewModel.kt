package org.dogadaev.simplesurance_dogs_collection.presentation.viewmodel

import androidx.lifecycle.ViewModel
import org.dogadaev.simplesurance_dogs_collection.data.repository.BreedsRepository

class FavoriteBreedsViewModel(
    private val breedsRepository: BreedsRepository
) : ViewModel() {
}