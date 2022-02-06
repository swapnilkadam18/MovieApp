package com.swapnil.movieapp.view.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.swapnil.movieapp.R
import com.swapnil.movieapp.databinding.FragmentMovieDetailsBinding
import com.swapnil.movieapp.model.network.service.MovieApiService
import com.swapnil.movielistapp.model.persistence.MovieDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MovieDetails : Fragment(R.layout.fragment_movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieDetailsBinding.bind(view)

        Log.e("TEST", "movie id: "+viewModel.movieId)

        binding.goBackBtn.setOnClickListener {
            //go back to prev screen
            findNavController().navigateUp()
        }

        binding.testBtn.setOnClickListener {
            val movieService = Retrofit.Builder()
                .baseUrl(MovieApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(MovieApiService::class.java)

            CoroutineScope(Dispatchers.IO).launch {

                val persistenceService = Room.databaseBuilder(
                    requireContext().applicationContext,
                    MovieDatabase::class.java,
                    "movies_database"
                ).build()

                val allMovies = persistenceService.movieDao().getTestAllMovies()
                Log.e("TEST", "allMovies size: "+allMovies.size)
                Log.e("TEST", "0 element id: "+allMovies.get(0).id)
                Log.e("TEST", "0 element id: "+allMovies.get(0).ogTitle)

                val movieItemNetwork = movieService.getMovieDetail(
                    movieId = allMovies.get(0).id,
                    apiKey = MovieApiService.API_KEY,
                    language = MovieApiService.LOCALE_LANGUAGE
                )
                Log.e("TEST", "list: "+movieItemNetwork.title)
            }
        }
    }
}