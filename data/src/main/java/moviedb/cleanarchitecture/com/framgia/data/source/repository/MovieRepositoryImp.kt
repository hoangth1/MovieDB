package moviedb.cleanarchitecture.com.framgia.data.source.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.CastEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.model.TrailerEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.source.local.MovieLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.MovieRemoteDatSource
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.model.Trailer
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository

class MovieRepositoryImp(val remote: MovieRemoteDatSource,
                         val local: MovieLocalDataSource,
                         private val movieEntityMapper: MovieEntityMapper,
                         private val castEntityMapper: CastEntityMapper,
                         private val trailerEntityMapper: TrailerEntityMapper
) : MovieRepository {
    override fun getTrailer(idMovie: String): Single<List<Trailer>> = remote.getTrailer(idMovie)
            .map { response ->
                response.listTrailer?.map { trailerEntityMapper.mapToDomain(it) }
            }

    override fun searchMovies(query: String, page: Int): Single<List<Movie>> = remote.searchMovie(query, page)
            .map { response ->
                response.listMovie.map { movieEntityMapper.mapToDomain(it) }
            }

    override fun getListMovieByGenre(genreId: String, page: Int): Single<List<Movie>> = remote.getListMovieByGenre(genreId, page)
            .map { response ->
                response.listMovie?.map { movieEntityMapper.mapToDomain(it) }
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
