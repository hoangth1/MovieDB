package moviedb.cleanarchitecture.com.framgia.data.source.remote

import moviedb.cleanarchitecture.com.framgia.data.source.MovieDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.MovieApi

class MovieRemoteDatSource(val movieApi: MovieApi) : MovieDataSource.Remote {
    override fun getListNowPlaying(page: Int) = movieApi.getListNowPlaying(page)

    override fun getListPopular(page: Int) = movieApi.getListPopular(page)

    override fun getListTopRated(page: Int) = movieApi.getListTopRated(page)

    override fun getListUpComing(page: Int) = movieApi.getListUpComing(page)
}
