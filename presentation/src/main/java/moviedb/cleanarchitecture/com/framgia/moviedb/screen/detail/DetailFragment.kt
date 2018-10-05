package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import moviedb.cleanarchitecture.com.framgia.moviedb.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentDetailBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.CastItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail.apdater.CastAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailcast.DetailCastFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainActivity
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.playtrailer.PlayTrailerFragment
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(), DetailHandler {

    companion object {
        const val BUNDLE_MOVIE = "movie"
        fun newInstance(movie: MovieItem) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_MOVIE, movie)
            }
        }
    }

    override val bindingVariable: Int = BR.viewModel
    override val viewModel by viewModel<DetailViewModel>()
    override val layoutId: Int = R.layout.fragment_detail
    lateinit var activityViewModel: MainViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is MainActivity) {
            (activity as MainActivity).apply {
                activityViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            }
        }
    }

    override fun initComponent(viewDataBinding: FragmentDetailBinding) {
        val bundle = arguments ?: return
        activityViewModel.isShowToolBar.value = false
        activityViewModel.haveStatusBar.value = true
        viewDataBinding.handler = this
        val currenMovie = bundle.getParcelable<MovieItem>(BUNDLE_MOVIE)
        val castAdapter = CastAdapter { openDetailCast(it) }
        viewDataBinding.recyclerCast.apply {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        viewModel.apply {
            movie.value = currenMovie
            listCast.observe(this@DetailFragment, Observer {
                castAdapter.submitList(it)
            })
            currenMovie?.id?.let { getCasts(it) }
        }
    }

    override fun onClickTrailer(movieItem: MovieItem) {
        replaceFragment(R.id.child_container, PlayTrailerFragment.newInstance(movieItem), "", true)
    }

    fun openDetailCast(castItem: CastItem) {
        replaceFragment(R.id.child_container, DetailCastFragment.newInstance(castItem), "", true)
    }

}
