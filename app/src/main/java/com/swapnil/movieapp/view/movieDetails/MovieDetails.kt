package com.swapnil.movieapp.view.movieDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.swapnil.movieapp.R
import com.swapnil.movieapp.databinding.FragmentMovieDetailsBinding
import com.swapnil.movieapp.model.repository.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetails : Fragment(R.layout.fragment_movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieDetailsBinding.bind(view)

        Log.e("TEST", "movie id: "+viewModel.movieId)

        binding.apply {
            viewModel.movieDetails.observe(
                viewLifecycleOwner
            ){result ->
                detailsProgress.isVisible = result is Resource.Loading
                result.data?.apply {
                    Glide.with(requireView())
                        .load(posterPath)
                        .into(detailsPoster)
                    detailsTitle.text = original_title
                    detailsDate.text = releasedDate
                    detailsOverview.text = overview
                }
            }
        }
    }
}