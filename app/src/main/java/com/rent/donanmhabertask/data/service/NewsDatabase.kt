package com.rent.donanmhabertask.data.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rent.donanmhabertask.data.model.Data
import com.rent.donanmhabertask.util.RequestConverter

@Database(entities = [Data::class],version = 1)
@TypeConverters(RequestConverter::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao() : NewsDao

    companion object {
        @Volatile private var instance : NewsDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context)  = Room.databaseBuilder(
            context.applicationContext,NewsDatabase::class.java,"newsdatabase"
        ).build()
    }



}