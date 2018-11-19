package moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.ViewDataBinding
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moviedb.cleanarchitecture.com.framgia.moviedb.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentFavoriteBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail.DetailFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite.adapter.FavoriteAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainActivity
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(), SwipeRefreshLayout.OnRefreshListener {


    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override val layoutId = R.layout.fragment_favorite
    override val viewModel by viewModel<FavoriteViewModel>()
    override val bindingVariable: Int = BR.viewModel
    lateinit var activityViewModel: MainViewModel
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is MainActivity) {
            (activity as MainActivity).apply {
                activityViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            }
        }
    }

    override fun initComponent(viewDataBinding: FragmentFavoriteBinding) {
        val favoriteAdapter = FavoriteAdapter(::openDetailMovie)
        viewDataBinding.apply {
            swipeRefreshLayout.setOnRefreshListener(this@FavoriteFragment)
            recyclerFavorite.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = favoriteAdapter
            }

        }
        activityViewModel.apply {
            haveStatusBar.value = true
            title.value = "Favorite"
            isShowToolBar.value = true
            isShowArrowBack.value = false
        }
        viewModel.apply {
            listFavoritMovie.observe(this@FavoriteFragment, Observer {
                favoriteAdapter.submitList(it)
            })
            isRefresh.observe(this@FavoriteFragment, Observer {
                viewDataBinding.swipeRefreshLayout.isRefreshing = false
            })
            getFavoriteMovie()
        }
    }

    fun openDetailMovie(movieItem: MovieItem) {
        replaceFragment(R.id.child_container, DetailFragment.newInstance(movieItem), "", true)
    }

    override fun onRefresh() {
        viewModel.getFavoriteMovie()
    }

}
