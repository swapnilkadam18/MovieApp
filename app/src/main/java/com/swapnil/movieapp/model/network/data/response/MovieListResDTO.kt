package com.swapnil.movielistapp.model.network.data.response

import com.google.gson.annotations.SerializedName

data class MovieListResDTO (
    @SerializedName("page") val listPage: Int? = null,
    @SerializedName("results") val movieListItemNetworkResult : List<MovieListItemResDTO>? = null
)