package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class CastUseCase(val movieRepository: MovieRepository)
    : UseCase<CastUseCase.Params, Single<List<Cast>>>() {
    override fun createObservable(params: Params?): Single<List<Cast>> {
        params?.let { return movieRepository.getListCast(params.idMovie) }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    data class Params(var idMovie: String)
}
