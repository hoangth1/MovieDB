package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailcast

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.databinding.library.baseAdapters.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentDetailCastBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.CastItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.GenreItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail.DetailFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailcast.adapter.MovieByPersonAdapter
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailgenre.DetailGenreFragment
import org.koin.android.viewmodel.ext.android.viewModel

class DetailCastFragment : BaseFragment<FragmentDetailCastBinding, DetailCastViewModel>() {
    companion object {
        const val BUNDLE_CAST = "cast"
        fun newInstance(castItem: CastItem) = DetailCastFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_CAST, castItem)
            }
        }
    }

    override val bindingVariable: Int = BR.viewModel
    override val viewModel by viewModel<DetailCastViewModel>()
    override val layoutId: Int = R.layout.fragment_detail_cast

    override fun initComponent(viewDataBinding: FragmentDetailCastBinding) {
        val bundle = arguments ?: return
        val cast = bundle.getParcelable<CastItem>(BUNDLE_CAST) ?: return
        val movieByPersonAdapter = MovieByPersonAdapter { openDetailMovie(it) }
        viewDataBinding.apply {
            recylerRelatedVideo.apply {
                adapter = movieByPersonAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
        viewModel.apply {
            listMovie.observe(this@DetailCastFragment, Observer {
                movieByPersonAdapter.submitList(it)
            })
            getPerson(cast.id ?: return)
        }
    }

    fun openDetailMovie(movieItem: MovieItem) {
        replaceFragment(R.id.child_container, DetailFragment.newInstance(movieItem), "", true)
    }
}