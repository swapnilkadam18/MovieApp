package com.swapnil.movielistapp.model.network.data.request

import com.google.gson.annotations.SerializedName

data class MovieDetailReqDTO (
    @SerializedName("api") val apiKey: String? = null,
    val movieId: Int? = null
)