package com.swapnil.movieapp.view.movieDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.swapnil.movieapp.model.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepo: MovieRepo,
    private val state: SavedStateHandle
):ViewModel() {
    val movieId = state.get<Int>("movie_id")
}