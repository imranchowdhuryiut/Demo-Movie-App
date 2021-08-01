package com.imran.demo.movie.list.data.network.services

import com.imran.demo.movie.list.data.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */
interface MovieService {

    @GET("/erik-sytnyk/movies-list/master/db.json")
    suspend fun getMovieList(): Response<MovieModel?>

}