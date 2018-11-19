package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class DeleteFavoriteUseCase(val repository: MovieRepository) : UseCase<DeleteFavoriteUseCase.Params, Int>() {
    override fun createObservable(params: Params?): Int {
        params?.let { return repository.deleteMovie(params.movie) }
        return -1
    }

    override fun onCleared() {
    }

    data class Params(var movie: Movie)
}