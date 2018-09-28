package moviedb.cleanarchitecture.com.framgia.data.source.remote.network

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.GenreResponse
import retrofit2.http.GET

interface GenreApi {
    @GET("genre/movie/list")
    fun getGenres(): Single<GenreResponse>
}
