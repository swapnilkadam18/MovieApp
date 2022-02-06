package com.swapnil.movieapp.model.network.service

import com.swapnil.movieapp.model.network.data.response.MovieDetailsResDTO
import com.swapnil.movielistapp.model.network.data.response.MovieListResDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    companion object{
        const val API_KEY: String = "a6d9584c234ffa42d2c4fb052ed62712"
        const val BASE_URL: String = "https://api.themoviedb.org/3/movie/"
        const val BASE_IMG_URL: String = "https://image.tmdb.org/t/p/w500"
        const val LOCALE_LANGUAGE = "en-US"
        const val PARAM_API_KEY = "api_key"
        const val PARAM_LANG = "language"
        const val PARAM_MOVIE_ID = "movie_id"
    }

    @GET("popular")
    suspend fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_LANG) language: String
    ): MovieListResDTO

    @GET("{movie_id}")
    suspend fun getMovieDetail(
        @Path(PARAM_MOVIE_ID) movieId: Int,
        @Query(PARAM_API_KEY) apiKey: String,
        @Query(PARAM_LANG) language: String
    ): MovieDetailsResDTO

}