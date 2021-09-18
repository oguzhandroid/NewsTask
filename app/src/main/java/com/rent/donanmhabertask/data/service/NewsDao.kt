package com.rent.donanmhabertask.data.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rent.donanmhabertask.data.model.Data
import java.util.*
import kotlin.collections.ArrayList

@Dao
interface NewsDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertNews(news: Data)

    @Query("SELECT * FROM data")
    suspend fun getAllNews() : List<Data>

    @Query("SELECT * FROM data WHERE id= :id")
    suspend fun getNews(id: Int): Data

    @Query("DELETE FROM data")
    suspend fun deleteAllNews()

    @Query("DELETE FROM data WHERE id= :id")
    suspend fun deleteNews(id : Int)

}