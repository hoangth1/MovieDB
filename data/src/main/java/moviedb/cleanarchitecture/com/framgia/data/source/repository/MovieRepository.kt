package moviedb.cleanarchitecture.com.framgia.data.source.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.source.MovieDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.local.MovieLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.MovieRemoteDatSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.MovieResponse

class MovieRepository(val remote: MovieRemoteDatSource,
                      val local: MovieLocalDataSource) : MovieDataSource.Remote, MovieDataSource.Local {
    override fun getListNowPlaying(page: Int) = remote.getListNowPlaying(page)
    override fun getListPopular(page: Int): Single<MovieResponse> = remote.getListPopular(page)
    override fun getListTopRated(page: Int) = remote.getListTopRated(page)
    override fun getListUpComing(page: Int) = remote.getListUpComing(page)
}
