package org.dogadaev.simplesurance_dogs_collection.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Breeds(
    @SerialName("message") val breeds: Map<String, Array<String>>?,
    @SerialName("status") val status: String?,
)