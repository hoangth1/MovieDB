package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class InsertFavoriteUseCase(val repository: MovieRepository) : UseCase<InsertFavoriteUseCase.Params, Long>() {
    override fun createObservable(params: Params?): Long {
        params?.let { return repository.inserMovie(params.movie) }
        return -1
    }

    override fun onCleared() {
    }

    data class Params(var movie: Movie)
}