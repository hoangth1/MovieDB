package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailgenre.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseRecyclerViewAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.ItemMovieGenreBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem

class MovieGenreAdapter(private val onItemClick: (MovieItem) -> Unit,
                        private val onFavoriteClick: (MovieItem) -> Unit) : BaseRecyclerViewAdapter<MovieItem>(
        object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem?, newItem: MovieItem?): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areContentsTheSame(oldItem: MovieItem?, newItem: MovieItem?): Boolean {
                return oldItem?.title == newItem?.title && oldItem?.overview == newItem?.overview
            }
        }
) {
    override fun bindFirstTime(viewBinding: ViewDataBinding) {
        if (viewBinding is ItemMovieGenreBinding) viewBinding.movieItem?.let { onItemClick.invoke(it) }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_genre, parent, false
        )
    }

    override fun handleChildClick(viewDataBinding: ViewDataBinding) {
        if (viewDataBinding is ItemMovieGenreBinding)
            viewDataBinding.imageFavorite.setOnClickListener {
                onFavoriteClick.invoke(viewDataBinding.movieItem ?: return@setOnClickListener)
            }
    }

    override fun bind(binding: ViewDataBinding, item: MovieItem) {
        if (binding is ItemMovieGenreBinding) binding.movieItem = item
    }
}