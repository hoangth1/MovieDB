package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailgenre

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import moviedb.cleanarchitecture.com.framgia.moviedb.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentDetailGenreBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.GenreItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail.DetailFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailgenre.adapter.MovieGenreAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainActivity
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailGenreFragment : BaseFragment<FragmentDetailGenreBinding, DetailGenreViewModel>() {
    companion object {
        const val BUNDLE_GENRE = "genre"
        fun newInstance(genreItem: GenreItem) = DetailGenreFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_GENRE, genreItem)
            }
        }
    }

    override val bindingVariable: Int = BR.viewModel
    override val viewModel by viewModel<DetailGenreViewModel>()
    override val layoutId: Int = R.layout.fragment_detail_genre
    lateinit var activityViewModel: MainViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is MainActivity) {
            (activity as MainActivity).apply {
                activityViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            }
        }
    }

    override fun initComponent(viewDataBinding: FragmentDetailGenreBinding) {
        val bundle = arguments ?: return
        val genre = bundle.getParcelable<GenreItem>(BUNDLE_GENRE) ?: return
        activityViewModel.apply {
            title.value = genre.name
            isShowToolBar.value = true
            isShowArrowBack.value = true
        }
        val movieGenreAdapter = MovieGenreAdapter { openDetailMovie(it) }
        viewDataBinding.apply {
            recyclerMovie.apply {
                adapter = movieGenreAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
        viewModel.apply {
            listMovie.observe(this@DetailGenreFragment, Observer {
                movieGenreAdapter.submitList(it)
            })
            getMovies(genre.id ?: return)
        }
    }
    fun openDetailMovie(movieItem: MovieItem) {
        replaceFragment(R.id.child_container, DetailFragment.newInstance(movieItem), "", false)
    }
}