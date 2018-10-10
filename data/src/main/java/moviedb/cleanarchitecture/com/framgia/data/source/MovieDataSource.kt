package moviedb.cleanarchitecture.com.framgia.data.source

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntity
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.*

interface MovieDataSource {
    interface Remote {
        fun getListNowPlaying(page: Int): Single<MovieResponse>
        fun getListPopular(page: Int): Single<MovieResponse>
        fun getListTopRated(page: Int): Single<MovieResponse>
        fun getListUpComing(page: Int): Single<MovieResponse>
        fun getListCast(movieId: String): Single<CastResponse>
        fun getListMovieByGenre(genreId: String, page: Int): Single<DetailGenreResponse>
        fun searchMovie(query: String, page: Int): Single<MovieResponse>
        fun getTrailer(idMovie: String): Single<TrailerResponse>
        fun getMovieByPerson(idPerson: String): Single<MovieByPersonResponse>
    }

    interface Local {
        fun getMovies(): Single<List<MovieEntity>>
        fun getMovie(idMovie: String): Single<MovieEntity>
        fun insertMovie(movieEntity: MovieEntity): Long
        fun deleteMovie(movieEntity: MovieEntity): Int
    }
}
