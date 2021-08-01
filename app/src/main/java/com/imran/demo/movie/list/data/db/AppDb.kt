package com.imran.demo.movie.list.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imran.demo.movie.list.data.db.dao.MovieItemDao
import com.imran.demo.movie.list.data.model.MovieItem

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */

@Database(
    entities = [
        MovieItem::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    StringListTypeConverter::class
)
abstract class AppDb: RoomDatabase() {
    abstract fun movieItemDao(): MovieItemDao
}

