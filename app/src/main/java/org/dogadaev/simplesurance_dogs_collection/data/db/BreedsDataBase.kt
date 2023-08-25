package org.dogadaev.simplesurance_dogs_collection.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.dogadaev.simplesurance_dogs_collection.data.db.model.DbBreed

@Database(
    version = 1,
    exportSchema = false,
    entities = [DbBreed::class]
)
abstract class BreedsDataBase: RoomDatabase() {
    abstract fun favoritesDao(): BreedsDao
}