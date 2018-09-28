package moviedb.cleanarchitecture.com.framgia.domain.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Genre

interface GenreRepository {
    fun getGenres(): Single<List<Genre>>
}
