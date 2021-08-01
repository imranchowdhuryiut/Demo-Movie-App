package com.imran.demo.movie.list.data.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */


data class MovieModel(
    @SerializedName("genres")
    var genres: List<String>? = null,
    @SerializedName("movies")
    var movies: List<MovieItem>? = null
)

@Entity(tableName = "movie_item")
@Parcelize
data class MovieItem(
    @SerializedName("actors")
    var actors: String? = null,
    @SerializedName("director")
    var director: String? = null,
    @SerializedName("genres")
    var genres: List<String>? = null,
    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("plot")
    var plot: String? = null,
    @SerializedName("posterUrl")
    var posterUrl: String? = null,
    @SerializedName("runtime")
    var runtime: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("year")
    var year: String? = null
): Parcelable