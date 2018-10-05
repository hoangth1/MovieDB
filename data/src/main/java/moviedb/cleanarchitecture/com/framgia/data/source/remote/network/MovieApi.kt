package moviedb.cleanarchitecture.com.framgia.data.source.remote.network

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.CastResponse
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.DetailGenreResponse
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.MovieResponse
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    fun getListNowPlaying(@Query(ApiConstants.PARAM_PAGE) page: Int): Single<MovieResponse>

    @GET("movie/popular")
    fun getListPopular(@Query(ApiConstants.PARAM_PAGE) page: Int): Single<MovieResponse>

    @GET("movie/top_rated")
    fun getListTopRated(@Query(ApiConstants.PARAM_PAGE) page: Int): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getListUpComing(@Query(ApiConstants.PARAM_PAGE) page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}/videos")
    fun getTrailer(@Path("movie_id") id: String): Single<TrailerResponse>

    @GET("movie/{movie_id}/credits")
    fun getCasts(@Path("movie_id") id: String): Single<CastResponse>

    @GET("discover/movie")
    fun getMovieByGenre(@Query(ApiConstants.PARAM_WITH_GENRE) genreId: String,
                        @Query(ApiConstants.PARAM_PAGE) page: Int): Single<DetailGenreResponse>

    @GET("search/movie")
    fun searchMovies(@Query(ApiConstants.PARAM_QUERY) query: String,
                     @Query(ApiConstants.PARAM_PAGE) page: Int): Single<MovieResponse>



}
