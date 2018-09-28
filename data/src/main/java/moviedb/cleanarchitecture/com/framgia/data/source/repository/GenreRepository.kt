package moviedb.cleanarchitecture.com.framgia.data.source.repository

import moviedb.cleanarchitecture.com.framgia.data.source.GenreDataSource

class GenreRepository(
        val remote: GenreDataSource.Remote,
        val local: GenreDataSource.Local) : GenreDataSource.Remote, GenreDataSource.Local {
    override fun getGenres() = remote.getGenres()
}

