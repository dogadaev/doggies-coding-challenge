package org.dogadaev.simplesurance_dogs_collection.presentation

sealed class UiState<out R> {
    object Loading : UiState<Nothing>()
    data class Data<T>(val data: T?) : UiState<T>()
    data class Error(val message: String?) : UiState<Nothing>()
}