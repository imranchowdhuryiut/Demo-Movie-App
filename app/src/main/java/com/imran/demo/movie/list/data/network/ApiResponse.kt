package com.imran.demo.movie.list.data.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Md. Imran Chowdhury on 8/1/2021.
 */

data class ApiResponse<T>(
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: T? = null,

)