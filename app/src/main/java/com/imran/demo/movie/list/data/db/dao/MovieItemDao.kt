package com.imran.demo.movie.list.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.imran.demo.movie.list.data.model.MovieItem

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */

@Dao
abstract class MovieItemDao: BaseDao<MovieItem>() {
    @Query("SELECT * FROM movie_item")
    abstract fun getAllMovieItem(): List<MovieItem>

    @Query("DELETE FROM movie_item")
    abstract fun deleteAll()

}