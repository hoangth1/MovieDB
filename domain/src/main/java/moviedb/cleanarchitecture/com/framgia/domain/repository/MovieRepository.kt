package moviedb.cleanarchitecture.com.framgia.domain.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie

interface MovieRepository {
    fun getListNowPlaying(page: Int): Single<List<Movie>>
    fun getListPopular(page: Int): Single<List<Movie>>
    fun getListTopRated(page: Int): Single<List<Movie>>
    fun getListUpComing(page: Int): Single<List<Movie>>
}
