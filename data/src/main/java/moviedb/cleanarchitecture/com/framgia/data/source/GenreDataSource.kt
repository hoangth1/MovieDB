package moviedb.cleanarchitecture.com.framgia.data.source

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.source.remote.response.GenreResponse

interface GenreDataSource {
    interface Remote {
        fun getGenres(): Single<GenreResponse>
    }

    interface Local {

    }
}
