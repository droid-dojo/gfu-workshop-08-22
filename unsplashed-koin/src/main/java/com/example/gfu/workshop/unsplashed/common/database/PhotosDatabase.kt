package com.example.gfu.workshop.unsplashed.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gfu.workshop.unsplashed.photo.data.db.DatabasePhoto
import com.example.gfu.workshop.unsplashed.photo.data.db.PhotoDao

@Database(entities = [DatabasePhoto::class], version = 1)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun photos(): PhotoDao
}