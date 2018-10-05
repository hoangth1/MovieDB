package moviedb.cleanarchitecture.com.framgia.data.source.remote

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.source.MovieDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.MovieApi
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.CastResponse
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.DetailGenreResponse
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.MovieResponse
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.TrailerResponse

class MovieRemoteDatSource(val movieApi: MovieApi) : MovieDataSource.Remote {
    override fun getTrailer(idMovie: String): Single<TrailerResponse> = movieApi.getTrailer(idMovie)

    override fun searchMovie(query: String,
                             page: Int): Single<MovieResponse> = movieApi.searchMovies(query, page)

    override fun getListMovieByGenre(genreId: String,
                                     page: Int): Single<DetailGenreResponse> = movieApi.getMovieByGenre(genreId, page)

    override fun getListCast(movieId: String): Single<CastResponse> = movieApi.getCasts(movieId)

    override fun getListNowPlaying(page: Int) = movieApi.getListNowPlaying(page)

    override fun getListPopular(page: Int) = movieApi.getListPopular(page)

    override fun getListTopRated(page: Int) = movieApi.getListTopRated(page)

    override fun getListUpComing(page: Int) = movieApi.getListUpComing(page)
}
