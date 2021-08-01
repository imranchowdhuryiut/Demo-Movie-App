package com.imran.demo.movie.list.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.imran.demo.movie.list.data.model.MovieItem
import com.imran.demo.movie.list.data.model.MovieModel
import com.imran.demo.movie.list.data.network.ApiResponse
import com.imran.demo.movie.list.data.network.LiveDataResource
import com.imran.demo.movie.list.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */
class MovieViewModel: ViewModel() {

    private val mRepo: MovieRepository by lazy { MovieRepository() }

    fun getMovieList(): LiveData<LiveDataResource<ApiResponse<MovieModel>>> {

        return requestApiData {
            mRepo.getMovieList()
        }

    }

    private fun <T> requestApiData(
        processData: ((T?) -> Unit)? = null,
        requestApiResponse: suspend () -> ApiResponse<T>?
    ): LiveData<LiveDataResource<ApiResponse<T>>> {
        return liveData(Dispatchers.Default) {
            emit(LiveDataResource.loading())
            val data = requestApiResponse()
            if (data?.status == true) {
                processData?.invoke(data.data)
                emit(LiveDataResource.success(data))
            } else emit(LiveDataResource.error(data))
        }
    }

    fun searchMoviesByGeneres(search: String):LiveData<List<MovieItem>> {
        return liveData(Dispatchers.Default) {
            emit(mRepo.searchMovieByGeneres(search))
        }
    }

}