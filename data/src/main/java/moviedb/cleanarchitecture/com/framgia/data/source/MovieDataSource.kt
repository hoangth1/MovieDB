package moviedb.cleanarchitecture.com.framgia.data.source

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.MovieResponse

interface MovieDataSource {
    interface Remote {
        fun getListNowPlaying(page: Int): Single<MovieResponse>
        fun getListPopular(page: Int): Single<MovieResponse>
        fun getListTopRated(page: Int): Single<MovieResponse>
        fun getListUpComing(page: Int): Single<MovieResponse>
    }

    interface Local {

    }
}
