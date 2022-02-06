package com.swapnil.movieapp.view.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.swapnil.movieapp.model.repository.MovieRepo
import com.swapnil.movielistapp.model.persistence.data.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val movieRepo: MovieRepo
): ViewModel() {

    private val movieEventChannel = Channel<MovieEvent> {  }
    val movieEvent = movieEventChannel.receiveAsFlow()

    val movies = movieRepo.getMovies().asLiveData()

    fun onMovieSelected(movie: MovieEntity) = viewModelScope.launch {
        movieEventChannel.send(MovieEvent.NavigateToMovieDetailScreen(movie.id))
    }

    sealed class MovieEvent{
        data class NavigateToMovieDetailScreen(val id: Int): MovieEvent()
    }
}