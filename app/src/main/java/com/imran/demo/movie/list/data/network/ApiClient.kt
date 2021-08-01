package com.imran.demo.movie.list.data.network

import com.google.gson.GsonBuilder
import com.imran.demo.movie.list.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */
object ApiClient {

    val mInstance: Retrofit by lazy {
        createInstance(Constants.BASE_URL)
    }

    private val gson by lazy {
        GsonBuilder()
            .create()
    }

    private fun createInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().build())
            .build()
    }

    inline fun <reified T> createApiService(): T {
        return mInstance.create(T::class.java)
    }
}