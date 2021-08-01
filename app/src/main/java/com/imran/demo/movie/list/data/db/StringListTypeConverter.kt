package com.imran.demo.movie.list.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */
class StringListTypeConverter {

    @TypeConverter
    fun fromStringItems(value: String?): List<String?>? {
        val token = object : TypeToken<List<String?>?>() {}.type
        return if (value == null) ArrayList<String?>() else Gson().fromJson<List<String>>(
            value,
            token
        )
    }

    @TypeConverter
    fun listToStringItems(data: List<String?>?): String? {
        return if (data == null) {
            ""
        } else {
            Gson().toJson(data)
        }
    }

}