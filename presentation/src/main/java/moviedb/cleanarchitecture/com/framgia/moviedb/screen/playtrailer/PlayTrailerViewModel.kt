package moviedb.cleanarchitecture.com.framgia.moviedb.screen.playtrailer

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.TrailerUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.TrailerItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.TrailerItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class PlayTrailerViewModel(val trailerUseCase: TrailerUseCase,
                           val scheduleProvider: ScheduleProvider,
                           val compositeDisposable: CompositeDisposable,
                           val trailerItemMapper: TrailerItemMapper) : BaseViewModel() {
    val listTrailer = MutableLiveData<List<TrailerItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val movie = MutableLiveData<MovieItem>()
    val currentTrailer = MutableLiveData<TrailerItem>()
    fun getLisTrailer(movieId: String) {
        trailerUseCase.createObservable(TrailerUseCase.Params(movieId))
                .subscribeOn(scheduleProvider.io())
                .observeOn(scheduleProvider.ui())
                .doOnSubscribe {
                    isLoading.value = true
                }
                .doAfterTerminate {
                    isLoading.value = false
                }
                .map { listTrailer ->
                    listTrailer.map { trailerItemMapper.mapToPresentation(it) }

                }.subscribe({
                    listTrailer.value = it
                }, { })
    }
}