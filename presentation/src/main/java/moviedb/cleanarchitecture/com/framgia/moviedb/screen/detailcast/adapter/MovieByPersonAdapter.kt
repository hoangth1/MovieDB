package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailcast.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseRecyclerViewAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.ItemMovieBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem

class MovieByPersonAdapter(val onItemClick: (MovieItem) -> Unit) : BaseRecyclerViewAdapter<MovieItem>(
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
            if (viewBinding is ItemMovieBinding)
                viewBinding.movieItem?.let{onItemClick.invoke(it)}
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: MovieItem) {
        if (binding is ItemMovieBinding) binding.movieItem=item
    }
}