package com.khushibaby.assignment.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khushibaby.assignment.model.MovieDataClass

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<MovieDataClass>)

    @Query("SELECT * FROM movie_list")
    fun getItems(): List<MovieDataClass>
}