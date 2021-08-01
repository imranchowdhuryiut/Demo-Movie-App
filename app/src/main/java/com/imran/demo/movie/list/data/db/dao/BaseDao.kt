package com.imran.demo.movie.list.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by Md. Imran Chowdhury on 5/9/2021.
 */

@Dao
public abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(data: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(vararg data: T)

    @Delete
    abstract fun delete(vararg data: T)

    @Delete
    abstract fun delete(data: List<T>)

}
