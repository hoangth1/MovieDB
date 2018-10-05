package moviedb.cleanarchitecture.com.framgia.moviedb.screen.search.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseRecyclerViewAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.ItemMovieGenreBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.ItemSearchResultBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem

class SearchResultAdapter(private val onItemClick: (MovieItem) -> Unit) : BaseRecyclerViewAdapter<MovieItem>(
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
        if (viewBinding is ItemSearchResultBinding) viewBinding.movieItem?.let { onItemClick.invoke(it) }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search_result, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: MovieItem) {
        if (binding is ItemSearchResultBinding) binding.movieItem = item
    }
}