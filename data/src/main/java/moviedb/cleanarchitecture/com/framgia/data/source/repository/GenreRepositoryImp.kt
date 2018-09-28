package moviedb.cleanarchitecture.com.framgia.data.source.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.GenreEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.source.GenreDataSource
import moviedb.cleanarchitecture.com.framgia.domain.model.Genre
import moviedb.cleanarchitecture.com.framgia.domain.repository.GenreRepository

class GenreRepositoryImp(val remote: GenreDataSource.Remote,
                         val local: GenreDataSource.Local,
                         private val genreEntityMapper: GenreEntityMapper) : GenreRepository {
    override fun getGenres(): Single<List<Genre>> = remote.getGenres().map {
        it.genres.map { genreEntityMapper.mapToDomain(it) }
    }
}
