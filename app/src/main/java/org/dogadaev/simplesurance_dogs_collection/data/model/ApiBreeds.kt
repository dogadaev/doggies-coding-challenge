package org.dogadaev.simplesurance_dogs_collection.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breed

@Serializable
data class ApiBreeds(
    @SerialName("message") val breeds: Map<String, Array<String>>?,
    @SerialName("status") val status: String?,
)

fun ApiBreeds.mapToUi() = breeds
    .orEmpty().keys
    .map {
        Breed(name = it)
    }