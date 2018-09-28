package moviedb.cleanarchitecture.com.framgia.moviedb.screen.home

import android.databinding.ViewDataBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment() : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    override val layoutId = R.layout.fragment_home
    override val viewModel by viewModel<HomeViewModel>()
    override val bindingVariable: Int = BR.viewModel
    override fun initComponent(viewDataBinding: ViewDataBinding) {
        viewModel.getGenres()
    }

}
