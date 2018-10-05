package moviedb.cleanarchitecture.com.framgia.moviedb.screen.home

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import moviedb.cleanarchitecture.com.framgia.moviedb.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentHomeBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.GenreItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail.DetailFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailgenre.DetailGenreFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.home.adapter.GenreAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.home.adapter.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment() : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    companion object {
        fun newInstance() = HomeFragment()
    }

    override val layoutId = R.layout.fragment_home
    override val viewModel by viewModel<HomeViewModel>()
    override val bindingVariable: Int = BR.viewModel
    override fun initComponent(viewDataBinding: FragmentHomeBinding) {
        val genreAdapter = GenreAdapter { openDetaiGenre(it) }
        val popularAdapter = MovieAdapter { openDetailMovie(it) }
        val nowPlayingAdapter = MovieAdapter { openDetailMovie(it) }
        val topRateAdapter = MovieAdapter { openDetailMovie(it) }
        val upComingAdapter = MovieAdapter { openDetailMovie(it) }
        viewDataBinding.apply {
            recyclerGenre.apply {
                adapter = genreAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            recyclerMostPopular.apply {
                adapter = popularAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            recyclerTopRated.apply {
                adapter = topRateAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            recyclerNowPlaying.apply {
                adapter = nowPlayingAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
            recyclerUpComing.apply {
                adapter = upComingAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
        viewModel.apply {
            listGenre.observe(this@HomeFragment, Observer {
                genreAdapter.submitList(it)
            })
            listPopular.observe(this@HomeFragment, Observer {
                popularAdapter.submitList(it)
            })
            listNowPlaying.observe(this@HomeFragment, Observer {
                nowPlayingAdapter.submitList(it)
            })
            listTopRated.observe(this@HomeFragment, Observer {
                topRateAdapter.submitList(it)
            })
            listUpComing.observe(this@HomeFragment, Observer {
                upComingAdapter.submitList(it)
            })
            getData()

        }
    }

    fun openDetailMovie(movieItem: MovieItem) {
        replaceFragment(R.id.child_container, DetailFragment.newInstance(movieItem), "", true)
    }

    fun openDetaiGenre(genre: GenreItem) {
        replaceFragment(R.id.child_container, DetailGenreFragment.newInstance(genre), "", true)
    }
}
