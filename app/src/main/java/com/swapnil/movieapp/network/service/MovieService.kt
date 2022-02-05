package com.swapnil.movielistapp.model.network.service

import com.swapnil.movielistapp.model.network.data.response.MovieListItemResDTO
import com.swapnil.movielistapp.model.network.data.response.MovieListResDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    companion object{
        const val API_KEY: String = "a6d9584c234ffa42d2c4fb052ed62712"
        const val BASE_URL: String = "https://api.themoviedb.org/3/movie/"
        const val LOCALE_LANGUAGE = "en-US"
    }

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieListResDTO

    @GET("{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): MovieListItemResDTO
}