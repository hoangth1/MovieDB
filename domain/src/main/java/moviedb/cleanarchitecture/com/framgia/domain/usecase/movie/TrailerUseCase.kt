package moviedb.cleanarchitecture.com.framgia.domain.usecase.movie

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Trailer
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class TrailerUseCase(val movieRepository: MovieRepository) : UseCase<TrailerUseCase.Params, Single<List<Trailer>>>() {
    override fun createObservable(params: Params?): Single<List<Trailer>> {
        params?.let { return movieRepository.getTrailer(params.idMovie) }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    data class Params(var idMovie: String)
}