package moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite

import android.databinding.ViewDataBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {
    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override val layoutId = R.layout.fragment_favorite
    override val viewModel by viewModel<FavoriteViewModel>()
    override val bindingVariable: Int = BR.viewModel
    override fun initComponent(viewDataBinding: FragmentFavoriteBinding) {
    }
}
