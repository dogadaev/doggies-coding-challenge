package org.dogadaev.simplesurance_dogs_collection.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedImages(
    @SerialName("message") val images: List<String>?,
    @SerialName("status") val status: String?,
)