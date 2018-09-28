package moviedb.cleanarchitecture.com.framgia.domain.usecase.genre

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Genre
import moviedb.cleanarchitecture.com.framgia.domain.repository.GenreRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class GenreUseCase(
        private val genreRepository: GenreRepository) : UseCase<
        GenreUseCase.Params, Single<List<Genre>>>() {
    override fun createObservable(params: Params?): Single<List<Genre>> {
        params?.let { return genreRepository.getGenres() }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    class Params()
}
