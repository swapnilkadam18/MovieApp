package com.swapnil.movieapp.view.movies

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.swapnil.movieapp.R
import com.swapnil.movieapp.databinding.FragmentMovieListBinding
import com.swapnil.movieapp.model.repository.Resource
import com.swapnil.movielistapp.model.persistence.data.MovieEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MoviesList : Fragment(R.layout.fragment_movie_list), MovieListAdapter.OnItemClickListener {

    private val moviesListViewModel : MoviesListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieListBinding.bind(view)
        val movieAdapter = MovieListAdapter(this)

        binding.apply {
            moviesList.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }

            moviesListViewModel.movies.observe(
                viewLifecycleOwner
            ){ result ->
                moviesListProgress.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                movieAdapter.submitList(result.data)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            moviesListViewModel.movieEvent.collect { event ->
                when (event) {
                    is MoviesListViewModel.MovieEvent.NavigateToMovieDetailScreen -> {
                        val action = MoviesListDirections.actionMovieListToMovieDetails(event.id)
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    override fun onItemClick(movie: MovieEntity) {
        moviesListViewModel.onMovieSelected(movie)
    }
}