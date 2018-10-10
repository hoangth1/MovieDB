package moviedb.cleanarchitecture.com.framgia.data.source.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.CastEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.model.TrailerEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.source.local.MovieLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.MovieRemoteDatSource
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.model.MovieInformation
import moviedb.cleanarchitecture.com.framgia.domain.model.Trailer
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository

class MovieRepositoryImp(val remote: MovieRemoteDatSource,
                         val local: MovieLocalDataSource,
                         private val movieEntityMapper: MovieEntityMapper,
                         private val castEntityMapper: CastEntityMapper,
                         private val trailerEntityMapper: TrailerEntityMapper
) : MovieRepository {
    override fun getFavoriteMovies(): Single<List<Movie>> = local.getMovies().map { listMovie ->
        listMovie.map { movieEntityMapper.mapToDomain(it) }
    }

    override fun getFavoriteMovie(idMovie: String): Single<Movie> = local.getMovie(idMovie).map {
        movieEntityMapper.mapToDomain(it)
    }

    override fun inserMovie(movie: Movie): Long = local.insertMovie(movieEntityMapper.mapToEntity(movie))

    override fun deleteMovie(movie: Movie): Int = local.deleteMovie(movieEntityMapper.mapToEntity(movie))

    override fun getMovieByPerson(idPerson: String): Single<List<Movie>> = remote.getMovieByPerson(idPerson)
            .map { response ->
                response.listMovie?.map { movieEntityMapper.mapToDomain(it) }
            }

    override fun getTrailer(idMovie: String): Single<List<Trailer>> = remote.getTrailer(idMovie)
            .map { response ->
                response.listTrailer?.map { trailerEntityMapper.mapToDomain(it) }
            }

    override fun searchMovies(query: String, page: Int): Single<List<Movie>> = remote.searchMovie(query, page)
            .map { response ->
                response.listMovie.map { movieEntityMapper.mapToDomain(it) }
            }

    override fun getListMovieByGenre(genreId: String, page: Int): Single<MovieInformation> = remote.getListMovieByGenre(genreId, page)
            .flatMap {
                val totalPage: Int? = it.totalPage
                val listMovie = it.listMovie?.map { movieEntityMapper.mapToDomain(it) }
                return@flatMap Single.just(MovieInformation(totalPage, listMovie))
            }

    override fun getListCast(idMovie: String): Single<List<Cast>> = remote.getListCast(idMovie)
            .map { response ->
                response.listCasts?.map { castEntityMapper.mapToDomain(it) }
            }

    override fun getListNowPlaying(page: Int) = remote.getListNowPlaying(page).map { response ->
        response.listMovie.map { movieEntityMapper.mapToDomain(it) }
    }

    override fun getListPopular(page: Int) = remote.getListPopular(page).map { response ->
        response.listMovie.map { movieEntityMapper.mapToDomain(it) }
    }

    override fun getListTopRated(page: Int) = remote.getListTopRated(page).map { response ->
        response.listMovie.map { movieEntityMapper.mapToDomain(it) }
    }

    override fun getListUpComing(page: Int) = remote.getListUpComing(page).map { response ->
        response.listMovie.map { movieEntityMapper.mapToDomain(it) }
    }
}
