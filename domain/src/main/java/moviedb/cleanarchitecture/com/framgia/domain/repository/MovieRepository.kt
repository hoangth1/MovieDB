package moviedb.cleanarchitecture.com.framgia.domain.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.model.Trailer

interface MovieRepository {
    fun getListNowPlaying(page: Int): Single<List<Movie>>
    fun getListPopular(page: Int): Single<List<Movie>>
    fun getListTopRated(page: Int): Single<List<Movie>>
    fun getListUpComing(page: Int): Single<List<Movie>>
    fun getListCast(idMovie: String): Single<List<Cast>>
    fun getListMovieByGenre(genreId: String, page: Int): Single<List<Movie>>
    fun searchMovies(query: String, page: Int): Single<List<Movie>>
    fun getTrailer(idMovie: String): Single<List<Trailer>>
    fun getMovieByPerson(idPerson: String): Single<List<Movie>>
}
