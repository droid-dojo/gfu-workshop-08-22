package com.example.gfu.workshop.unsplashed.photo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Insert(onConflict = REPLACE)
    suspend fun addPhoto(photo: DatabasePhoto)

    @Insert(onConflict = REPLACE)
    suspend fun addPhotos(photos: List<DatabasePhoto>)

    @Query("SELECT * FROM DatabasePhoto")
    suspend fun allPhotos(): List<DatabasePhoto>

    @Query("SELECT * FROM DatabasePhoto")
    fun photos(): Flow<List<DatabasePhoto>>

}