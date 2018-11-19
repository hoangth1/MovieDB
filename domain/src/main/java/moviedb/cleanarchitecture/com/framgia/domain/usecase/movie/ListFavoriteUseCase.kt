package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class ListFavoriteUseCase(val movieRepository: MovieRepository) : UseCase<ListFavoriteUseCase.Params, Single<List<Movie>>>() {
    override fun createObservable(params: Params?): Single<List<Movie>> {
        params?.let { return movieRepository.getFavoriteMovies() }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    class Params()

}