package moviedb.cleanarchitecture.com.framgia.data.source.remote.network

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.MovieResponse
import retrofit2.http.GET
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
}
