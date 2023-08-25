package org.dogadaev.simplesurance_dogs_collection.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.dogadaev.simplesurance_dogs_collection.data.repository.model.Breed

@Entity(
    tableName = "breeds"
)
data class DbBreed(
    @PrimaryKey val name: String = "",
    val isFavorite: Boolean = false,
)

fun List<DbBreed>.mapToUi() = map {
    Breed(
        name = it.name,
        isFavorite = it.isFavorite
    )
}

fun Breed.mapTpDb() = DbBreed(
    name = name,
    isFavorite = isFavorite
)
