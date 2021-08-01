package com.imran.demo.movie.list.data.repository

import com.imran.demo.movie.list.MovieApp
import com.imran.demo.movie.list.data.model.MovieItem
import com.imran.demo.movie.list.data.model.MovieModel
import com.imran.demo.movie.list.data.network.ApiResponse
import com.imran.demo.movie.list.data.network.services.MovieService
import com.imran.demo.movie.list.utils.createApiService
import java.util.*

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */
class MovieRepository {

    private val mWebService = createApiService<MovieService>()

    suspend fun getMovieList(): ApiResponse<MovieModel> {
        val lastFetchedTime = MovieApp.appPref.getLogTime()
        val currentTime = Calendar.getInstance().time.time
        val period = 30 * 60 * 1000

        if (lastFetchedTime + period > currentTime) {
            val data = MovieApp.appDb.movieItemDao().getAllMovieItem()
            val genres = MovieApp.appPref.getGenres()
            val model = MovieModel()
            model.genres = genres?.toList()
            model.movies = data
            return ApiResponse(true, "", model)
        } else {
            val response = mWebService.getMovieList()
            return if (response.isSuccessful) {
                MovieApp.appDb.movieItemDao().deleteAll()
                MovieApp.appPref.setGenres(null)
                response.body()?.movies?.let { MovieApp.appDb.movieItemDao().save(it) }
                MovieApp.appPref.setGenres(response.body()?.genres?.toSet())
                MovieApp.appPref.logTime(Date().time)
                ApiResponse(true, "", response.body())
            } else {
                ApiResponse(false, "Something went wrong", null)
            }
        }
    }

    fun searchMovieByGeneres(search: String): List<MovieItem> {
        return if (search != "Generes") {
            MovieApp.appDb.movieItemDao().getAllMovieItem().filter {
                it.genres?.contains(search) == true
            }
        } else {
            MovieApp.appDb.movieItemDao().getAllMovieItem()
        }
    }

}