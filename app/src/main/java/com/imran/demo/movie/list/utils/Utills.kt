package com.imran.demo.movie.list.utils

import com.imran.demo.movie.list.data.network.ApiClient

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */


inline fun <reified T> createApiService(): T = ApiClient.createApiService()