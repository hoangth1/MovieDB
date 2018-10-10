package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail.apdater

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseRecyclerViewAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.ItemCastBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.CastItem

class CastAdapter(val onItemClick: (CastItem) -> Unit) : BaseRecyclerViewAdapter<CastItem>(
        object : DiffUtil.ItemCallback<CastItem>() {
            override fun areContentsTheSame(oldItem: CastItem?, newItem: CastItem?): Boolean {
                return oldItem?.id == newItem?.id
            }

            override fun areItemsTheSame(oldItem: CastItem?, newItem: CastItem?): Boolean {
                return oldItem?.name == newItem?.name
            }
        }
) {
    override fun bindFirstTime(viewBinding: ViewDataBinding) {
        if (viewBinding is ItemCastBinding) onItemClick.invoke(viewBinding.castItem ?: return)

    }

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_cast, parent, false)
    }

    override fun bind(binding: ViewDataBinding, item: CastItem) {
        if (binding is ItemCastBinding)
            binding.castItem = item
    }
}
