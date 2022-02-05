package com.swapnil.movieapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.swapnil.movieapp.R
import com.swapnil.movieapp.databinding.FragmentMovieDetailsBinding

class MovieDetails : Fragment(R.layout.fragment_movie_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieDetailsBinding.bind(view)

        binding.goBackBtn.setOnClickListener {
            //go back to prev screen
            findNavController().navigateUp()
        }
    }
}