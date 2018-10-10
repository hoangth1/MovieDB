package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class SearchMovieUseCase(val movieRepository: MovieRepository) : UseCase<SearchMovieUseCase.Params, Single<List<Movie>>>() {
    override fun createObservable(params: Params?): Single<List<Movie>> {
        params?.let { return movieRepository.searchMovies(params.query, params.page) }
        return Single.error(Throwable())
    }

    override fun onCleared() {
    }

    data class Params(var query: String, var page: Int)
}
