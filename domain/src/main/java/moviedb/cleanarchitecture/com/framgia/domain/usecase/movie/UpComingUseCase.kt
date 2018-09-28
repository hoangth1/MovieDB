package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class UpComingUseCase(
        private val movieRepository: MovieRepository) : UseCase<UpComingUseCase.Params,
        Single<List<Movie>>>() {
    override fun createObservable(params: UpComingUseCase.Params?): Single<List<Movie>> {
        params?.apply { return movieRepository.getListNowPlaying(page) }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {}

    data class Params(var page: Int)
}
