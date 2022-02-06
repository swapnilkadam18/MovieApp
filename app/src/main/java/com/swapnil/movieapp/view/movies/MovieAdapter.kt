package com.swapnil.movieapp.view.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swapnil.movieapp.databinding.MovieListItemBinding
import com.swapnil.movielistapp.model.persistence.data.MovieEntity

class MovieListAdapter(
    private val listener: OnItemClickListener
    ): ListAdapter<MovieEntity, MovieListAdapter.MovieListViewHolder>(MovieComparator()) {

    interface OnItemClickListener {
        fun onItemClick(movie: MovieEntity)
    }

    inner class MovieListViewHolder(
        private val binding: MovieListItemBinding
        ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onItemClick(task)
                    }
                }
            }
        }

        fun bind(movie: MovieEntity) {
            binding.apply {
                Glide.with(itemView)
                    .load(movie.backDropPath)
                    .into(moviePoster)
                movieTitle.text = movie.ogTitle
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class MovieComparator : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
            oldItem == newItem
    }
}