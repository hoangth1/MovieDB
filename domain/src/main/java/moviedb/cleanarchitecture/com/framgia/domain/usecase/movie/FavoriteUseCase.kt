package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class FavoriteUseCase(val repository: MovieRepository) : UseCase<FavoriteUseCase.Params, Single<Movie>>() {
    override fun createObservable(params: Params?): Single<Movie> {
        params?.let { return repository.getFavoriteMovie(params.movieId) }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    data class Params(var movieId: String)

}