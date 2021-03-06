package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.model.MovieInformation
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class DetailGenreUseCase(private val movieRepository: MovieRepository) : UseCase<DetailGenreUseCase.Params, Single<MovieInformation>>() {
    override fun createObservable(params: Params?): Single<MovieInformation> {
        params?.let { return movieRepository.getListMovieByGenre(it.idMovie, it.page) }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    data class Params(var idMovie: String, var page: Int)
}
