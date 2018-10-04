package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.CastUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.CastItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class DetailViewModel(
        val castUseCase: CastUseCase,
        val scheduleProvider: ScheduleProvider,
        val compositeDisposable: CompositeDisposable,
        val castItemMapper: CastItemMapper
) : BaseViewModel() {
    val movie = MutableLiveData<MovieItem>()
    val isLoading = MutableLiveData<Boolean>()
    val listCast = MutableLiveData<List<Cast>>()

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
                    .subscribe({
                        listCast.value = it
                    }, { }))
}
