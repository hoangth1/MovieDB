package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class NowPlayingUseCase(
        private val movieRepository: MovieRepository) : UseCase<NowPlayingUseCase.Params,
        Single<List<Movie>>>() {
    override fun createObservable(params: NowPlayingUseCase.Params?): Single<List<Movie>> {
        params?.let { return movieRepository.getListNowPlaying(it.page) }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {}

    data class Params(var page: Int)
}
