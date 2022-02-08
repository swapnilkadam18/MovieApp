package com.swapnil.movieapp.model.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.swapnil.movieapp.model.network.service.MovieApiService
import com.swapnil.movieapp.model.persistence.MovieDao
import com.swapnil.movielistapp.model.network.data.response.MovieListResDTO
import com.swapnil.movielistapp.model.persistence.MovieDatabase
import com.swapnil.movielistapp.model.persistence.data.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.File
import java.io.FileReader
import java.lang.reflect.Type


@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepoTest {

    @InjectMocks
    lateinit var mockRepo: MovieRepo

    @Mock
    lateinit var mockApiService: MovieApiService

    @Mock
    lateinit var mockDatabase: MovieDatabase

//    @Mock
    lateinit var dao: MovieDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dao = mockDatabase?.movieDao()
    }

    @After
    fun tearDown() {
        MockitoAnnotations.openMocks(this).close()
    }


    @Test
    fun getMovies() {
        val dummyKey = "12312313"
        val dummyLang = "en-us"
        val moviesResPath = "/Users/swapnilkadam/Desktop/Android_Projects/MovieApp/app/src/main/assets/MoviesResponse.json"
        val moviesRes = getJsonResponse(moviesResPath)
        val mockMovieEntity = MovieEntity(634649, "/2Jp2RIwJ3Dt7vamkTt7llVJ7uY.jpg",
            "en","Spider-Man: No Way Home","testOverview", 7.4,
            "/nvkjasd.jpg","08-09-2021","testTitle",false,
            7.0,50)
        val flowRes = mutableListOf(listOf(mockMovieEntity)).asFlow()

        runTest {
            Mockito.`when`(mockApiService.getPopularMovies(dummyKey,dummyLang)).
            thenReturn(moviesRes)
            Mockito.`when`(dao.getAllMovies()).
                thenReturn(flowRes)

            mockRepo.getMovies().test {
               //test for the events emitted
                //test for the value emitted by comparing with the response from moviesRes
            }
        }

    }

    @Test
    fun getMovieDetails() {
        //copy the response from the api for the movie id used
        //use the getJsonResponse method to parse the response
        //pass it to the mockito to mock as a return response
        //then in runtest block call the method and test it by comparing the response received from
        //the step 2. Val provided will be equal to the value fetched from the db which is the custom entity.
    }

    private fun getJsonResponse(filePath: String): MovieListResDTO? {
        val dir = File(filePath)
        val REVIEW_TYPE: Type = object : TypeToken<MovieListResDTO?>() {}.getType()
        val gson = Gson()
        var reader: JsonReader? = null
        return try {
            reader = JsonReader(FileReader(dir))
            gson.fromJson(reader, REVIEW_TYPE) // prints to screen some values
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}