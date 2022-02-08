package com.swapnil.movieapp.model.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.swapnil.movieapp.model.persistence.data.MovieDetailEntity
import com.swapnil.movielistapp.model.persistence.MovieDatabase
import com.swapnil.movielistapp.model.persistence.data.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var mockDatabase: MovieDatabase
    private lateinit var mockDao: MovieDao

    @Before
    fun setup(){
        mockDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        mockDao = mockDatabase.movieDao()
    }

    @After
    fun tearDown(){
        mockDatabase.close()
    }

    @Test
    fun insertAndFetchMovies() = runTest{
        val mockMovieEntity = MovieEntity(1, "/asda.jpg","eng","testMovie","testOverview", 8.0,"/nvkjasd.jpg","08-09-2021","testTitle",false,7.0,50)
        val mockMovieList : List<MovieEntity> = mutableListOf(mockMovieEntity)
        mockDao.insertMovies(mockMovieList)
        mockDao.getAllMovies().test {
            assertThat(expectMostRecentItem()).isEqualTo(mockMovieList)
        }
    }

    @Test
    fun insertAndFetchMovieDetails() = runTest{
        val movieDetails = MovieDetailEntity(1, "testTitle","testOverview","/asda.jpg","08-09-2021")
        mockDao.insertMovieDetails(movieDetails)
        mockDao.getMovieDetails(1).test {
            assertThat(expectMostRecentItem()).isEqualTo(movieDetails)
        }
    }

}