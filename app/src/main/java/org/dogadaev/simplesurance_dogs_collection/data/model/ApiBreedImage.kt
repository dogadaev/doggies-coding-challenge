package org.dogadaev.simplesurance_dogs_collection.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiBreedImage(
    @SerialName("message") val image: String?,
    @SerialName("status") val status: String?,
)
