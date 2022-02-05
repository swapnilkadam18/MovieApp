package com.swapnil.movielistapp.model.network.data.request

import com.google.gson.annotations.SerializedName

data class MovieListReqDTO (
    @SerializedName("api")val apiKey : String? = null
)