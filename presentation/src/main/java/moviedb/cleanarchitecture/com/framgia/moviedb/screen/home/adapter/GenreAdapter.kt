package moviedb.cleanarchitecture.com.framgia.moviedb.screen.home.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseRecyclerViewAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.ItemGenreBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.GenreItem

class GenreAdapter(val onItemClick: (GenreItem) -> Unit) : BaseRecyclerViewAdapter<GenreItem>(
        object : DiffUtil.ItemCallback<GenreItem>() {
            override fun areContentsTheSame(oldItem: GenreItem?, newItem: GenreItem?): Boolean {
                return oldItem?.name == newItem?.name
            }

            override fun areItemsTheSame(oldItem: GenreItem?, newItem: GenreItem?): Boolean {
                return oldItem?.id == newItem?.id
            }
        }
) {
    override fun bindFirstTime(viewBinding: ViewDataBinding) {
        if (viewBinding is ItemGenreBinding) viewBinding.genreItem?.let { onItemClick.invoke(it) }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_genre, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: GenreItem) {
        if (binding is ItemGenreBinding) binding.genreItem = item
    }

}
