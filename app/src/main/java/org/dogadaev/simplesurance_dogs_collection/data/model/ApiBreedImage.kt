package org.dogadaev.simplesurance_dogs_collection.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.BreedImage

@Serializable
data class ApiBreedImage(
    @SerialName("message") val image: String?,
    @SerialName("status") val status: String?,
)

fun ApiBreedImage.mapToUi(breedName: String) = BreedImage(
    name = breedName,
    image = image
)