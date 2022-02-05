package com.swapnil.movieapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.gson.GsonBuilder
import com.swapnil.movieapp.R
import com.swapnil.movieapp.databinding.FragmentMovieDetailsBinding
import com.swapnil.movielistapp.model.network.service.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetails : Fragment(R.layout.fragment_movie_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieDetailsBinding.bind(view)

        binding.goBackBtn.setOnClickListener {
            //go back to prev screen
            findNavController().navigateUp()
        }

        binding.testBtn.setOnClickListener {
            val movieService = Retrofit.Builder()
                .baseUrl(MovieService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(MovieService::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                val movieItemNetwork = movieService.getMovieDetail(movieId = 634649,apiKey = MovieService.API_KEY,
                    language = MovieService.LOCALE_LANGUAGE
                )
                Log.e("TEST", "list: "+movieItemNetwork.title)
            }
        }
    }
}