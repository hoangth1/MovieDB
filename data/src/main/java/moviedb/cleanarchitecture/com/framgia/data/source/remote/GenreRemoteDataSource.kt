package moviedb.cleanarchitecture.com.framgia.data.source.remote

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.source.GenreDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.GenreApi
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.GenreResponse

class GenreRemoteDataSource(val genreApi: GenreApi) : GenreDataSource.Remote, GenreDataSource.Local {
    override fun getGenres() = genreApi.getGenres()
}
