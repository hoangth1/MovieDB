package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.CastUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.DeleteFavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.FavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.InsertFavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.CastItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.CastItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class DetailViewModel(
        val insertFavoriteUseCase: InsertFavoriteUseCase,
        val deleteFavoriteUseCase: DeleteFavoriteUseCase,
        val favoriteUseCase: FavoriteUseCase,
        val castUseCase: CastUseCase,
        val scheduleProvider: ScheduleProvider,
        val compositeDisposable: CompositeDisposable,
        val castItemMapper: CastItemMapper,
        val movieItemMapper: MovieItemMapper
) : BaseViewModel() {
    val movie = MutableLiveData<MovieItem>()
    val isLoading = MutableLiveData<Boolean>()
    val listCast = MutableLiveData<List<CastItem>>()

    fun getCasts(movieId: String) =
            compositeDisposable.add(castUseCase.createObservable(CastUseCase.Params(movieId))
                    .subscribeOn(scheduleProvider.io())
                    .observeOn(scheduleProvider.ui())
                    .doOnSubscribe {
                        isLoading.value = true
                    }
                    .doAfterTerminate {
                        isLoading.value = false
                    }
                    .map { listCast ->
                        listCast.map { castItemMapper.mapToPresentation(it) }
                    }
                    .subscribe({
                        listCast.value = it
                    }, { }))

    fun checkVideoAddedFavorite() {
        favoriteUseCase.createObservable(FavoriteUseCase.Params(movie.value?.id ?: return))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movie.value?.isFavorite?.set(true)
                }, { movie.value?.isFavorite?.set(false) })
    }

    fun addFavorite(movieItem: MovieItem) {
        Observable.create<Long> { emitter ->
            emitter.onNext(insertFavoriteUseCase.createObservable(InsertFavoriteUseCase.Params(movieItemMapper.mapToDomain(movieItem))))
        }.subscribeOn(scheduleProvider.io()).observeOn(scheduleProvider.ui())
                .subscribe({
                    movieItem.isFavorite.set(true)
                }, {
                })
    }

    fun removeFavorite(movieItem: MovieItem) {
        Observable.create<Int> { emitter ->
            emitter.onNext(deleteFavoriteUseCase.createObservable(DeleteFavoriteUseCase.Params(movieItemMapper.mapToDomain(movieItem))))
        }.subscribeOn(scheduleProvider.io()).observeOn(scheduleProvider.ui())
                .subscribe({
                    movieItem.isFavorite.set(false)
                }, {
                })
    }
}
