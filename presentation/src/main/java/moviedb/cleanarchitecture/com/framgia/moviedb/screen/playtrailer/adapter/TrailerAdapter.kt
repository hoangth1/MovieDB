package moviedb.cleanarchitecture.com.framgia.moviedb.screen.playtrailer.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseRecyclerViewAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.ItemTrailerBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.TrailerItem

class TrailerAdapter(val onItemClick: (TrailerItem) -> Unit) : BaseRecyclerViewAdapter<TrailerItem>(
        object : DiffUtil.ItemCallback<TrailerItem>() {
            override fun areItemsTheSame(oldItem: TrailerItem?, newItem: TrailerItem?): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areContentsTheSame(oldItem: TrailerItem?, newItem: TrailerItem?): Boolean {
                return oldItem?.name == newItem?.name
            }

        }
) {
    override fun bindFirstTime(viewBinding: ViewDataBinding) {
        if (viewBinding is ItemTrailerBinding) onItemClick.invoke(viewBinding.trailerItem ?: return)
    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_trailer, parent, false
        )
    }

    override fun bind(binding: ViewDataBinding, item: TrailerItem) {
        if (binding is ItemTrailerBinding) binding.trailerItem = item
    }
}