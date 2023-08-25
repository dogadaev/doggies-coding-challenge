package org.dogadaev.simplesurance_dogs_collection.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breeds

@Serializable
data class ApiBreeds(
    @SerialName("message") val breeds: Map<String, Array<String>>?,
    @SerialName("status") val status: String?,
)

fun ApiBreeds.mapToUi() = Breeds(
    breeds = breeds.orEmpty()
)