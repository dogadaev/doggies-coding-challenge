package org.dogadaev.simplesurance_dogs_collection.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.dogadaev.simplesurance_dogs_collection.data.db.model.DbBreed

@Dao
interface BreedsDao {

    @Query("SELECT * FROM  breeds")
    fun getBreeds(): Flow<List<DbBreed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBreeds(breeds: List<DbBreed>)

    @Query("DELETE FROM breeds WHERE name = :name;")
    suspend fun removeBreed(name: String)
}