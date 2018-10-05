package moviedb.cleanarchitecture.com.framgia.moviedb.screen.search.adapter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import moviedb.cleanarchitecture.com.framgia.moviedb.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentSearchBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail.DetailFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainActivity
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.search.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    companion object {
        const val BUNDLE_QUERY = "genre"
        fun newInstance(query: String) = SearchFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_QUERY, query)
            }
        }
    }

    override val bindingVariable: Int = BR.viewModel
    override val viewModel: SearchViewModel by viewModel()
    override val layoutId: Int = R.layout.fragment_search
    lateinit var activityViewModel: MainViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is MainActivity) {
            (activity as MainActivity).apply {
                activityViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            }
        }
    }

    override fun initComponent(viewDataBinding: FragmentSearchBinding) {
        val bundle = arguments ?: return
        val query = bundle.getString(BUNDLE_QUERY) ?: return
        activityViewModel.apply {
            isShowArrowBack.value = true
            isShowToolBar.value = true
            title.value = query
        }
        val searchResultAdapter = SearchResultAdapter { openDetailMovie(it) }
        viewDataBinding.apply {
            recyclerSearchResult.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = searchResultAdapter
            }
        }
        viewModel.apply {
            listMovieResult.observe(this@SearchFragment, Observer {
                searchResultAdapter.submitList(it)
            })
            searchMovies(query)
        }

    }

    fun openDetailMovie(movieItem: MovieItem) {
        replaceFragment(R.id.child_container, DetailFragment.newInstance(movieItem), "", true)
    }
}