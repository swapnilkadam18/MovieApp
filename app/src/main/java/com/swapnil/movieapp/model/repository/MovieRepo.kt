package com.swapnil.movieapp.model.repository

import androidx.room.withTransaction
import com.swapnil.movieapp.model.network.service.MovieApiService
import com.swapnil.movielistapp.model.persistence.MovieDatabase
import com.swapnil.movielistapp.model.persistence.data.MovieEntity
import javax.inject.Inject

class MovieRepo @Inject constructor(
    private val networkApiService: MovieApiService,
    private val movieDatabase: MovieDatabase
) {

    private val movieDao = movieDatabase.movieDao()

    fun getMovies() = networkBoundResource(
        query = {
            movieDao.getAllMovies()
        },
        fetch = {
            networkApiService.getPopularMovies(
                MovieApiService.API_KEY,
                MovieApiService.LOCALE_LANGUAGE
            )
        },
        saveFetchResult = { moviesFromApi ->
            val moviesEntityVal: MutableList<MovieEntity> = mutableListOf()
            val iterator = (moviesFromApi.movieListItemNetworkResult)!!.iterator()
            iterator.forEach { apiMovItem ->
                apiMovItem.apply {
                    moviesEntityVal.add(
                        MovieEntity(
                            id!!,
                            MovieApiService.BASE_IMG_URL+backdropPath!!,
                            originalLanguage!!,
                            originalTitle!!,
                            overview!!,
                            popularity!!,
                            posterPath!!,
                            releaseDate!!,
                            title!!,
                            video!!,
                            voteAverage!!,
                            voteCount!!
                        )
                    )
                }
            }

            movieDatabase.withTransaction {
                movieDao.deleteAllMovies()
                movieDao.insertMovies(movies = moviesEntityVal)
            }
        }
    )

}