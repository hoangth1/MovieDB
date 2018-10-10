package moviedb.cleanarchitecture.com.framgia.moviedb.screen.playtrailer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.android.youtube.player.YouTubePlayerView
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.ApiConstants
import moviedb.cleanarchitecture.com.framgia.moviedb.BR
import moviedb.cleanarchitecture.com.framgia.moviedb.R
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseFragment
import moviedb.cleanarchitecture.com.framgia.moviedb.databinding.FragmentPlayTrailerBinding
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.TrailerItem
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainActivity
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.playtrailer.adapter.TrailerAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class PlayTrailerFragment : BaseFragment<FragmentPlayTrailerBinding, PlayTrailerViewModel>(),
        YouTubePlayer.OnInitializedListener {

    override val bindingVariable: Int = BR.viewModel
    override val viewModel by viewModel<PlayTrailerViewModel>()
    override val layoutId: Int = R.layout.fragment_play_trailer
    private lateinit var activityViewModel: MainViewModel

    companion object {
        const val BUNDLE_MOVIE = "movie"
        fun newInstance(movieItem: MovieItem) = PlayTrailerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_MOVIE, movieItem)
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is MainActivity) {
            (activity as MainActivity).apply {
                activityViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            }
        }
    }

    override fun initComponent(viewDataBinding: FragmentPlayTrailerBinding) {
        val bundle = arguments ?: return
        val movie = bundle.getParcelable<MovieItem>(BUNDLE_MOVIE)
        viewModel.movie.value = movie
        val trailerAdapter = TrailerAdapter { playTrailer(it) }
        val youtubePlayerFragment = YouTubePlayerSupportFragment()
        youtubePlayerFragment.initialize(ApiConstants.YOUTUBE_API_KEY, this)
        replaceChildFragment(this, R.id.container_video, youtubePlayerFragment, "", false)
        activityViewModel.apply {
            isShowToolBar.value = false
            isShowArrowBack.value = false
            haveStatusBar.value = false
        }
        viewDataBinding.apply {
            recyclerListTrailer.apply {
                adapter = trailerAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
        viewModel.apply {
            listTrailer.observe(this@PlayTrailerFragment, Observer { listTrailer ->
                trailerAdapter.submitList(listTrailer)
                listTrailer?.apply {
                    if (size > 0) currentTrailer.value = get(0)
                }
            })
            getLisTrailer(movie?.id ?: return)


        }

    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, isSuccess: Boolean) {

        viewModel.currentTrailer.observe(this, Observer {
            if (!isSuccess) player?.cueVideo(it?.key)

        })

    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
    }

    private fun playTrailer(trailerItem: TrailerItem) {
        viewModel.currentTrailer.value = trailerItem
    }

}