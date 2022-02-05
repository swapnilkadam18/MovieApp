package com.swapnil.movieapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.swapnil.movieapp.databinding.ActivityMainBinding
import com.swapnil.movielistapp.model.network.service.MovieService
import com.swapnil.movielistapp.model.network.service.MovieService.Companion.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieService::class.java)

        binding.testBtn.setOnClickListener {
                CoroutineScope(IO).launch {
                val movieListNetwork = movieService.getPopularMovies(apiKey = "a6d9584c234ffa42d2c4fb052ed62712",
                    language = "en-US")
                Log.e("TEST", "list: "+movieListNetwork.movieListItemNetworkResult!!.get(0).title)

                val movieItemNetwork = movieService.getMovieDetail(movieId = 634649,apiKey = MovieService.API_KEY,
                    language = MovieService.LOCALE_LANGUAGE
                )
                Log.e("TEST", "list: "+movieItemNetwork.title)
            }
        }
    }
}