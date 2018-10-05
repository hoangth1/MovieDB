package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailgenre

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.DetailGenreUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class DetailGenreViewModel(
        val detailGenreUseCase: DetailGenreUseCase,
        val scheduleProvider: ScheduleProvider,
        val compositeDisposable: CompositeDisposable,
        val movieItemMapper: MovieItemMapper
) : BaseViewModel() {
    val listMovie = MutableLiveData<List<MovieItem>>()
    val isLoading = MutableLiveData<Boolean>()
    fun getMovies(genreId: String) {
        detailGenreUseCase.createObservable(DetailGenreUseCase.Params(genreId, 1))
                .subscribeOn(scheduleProvider.io())
                .observeOn(scheduleProvider.ui())
                .map { listMovie ->
                    listMovie.map { movieItemMapper.mapToPresentation(it) }
                }
                .doOnSubscribe {
                    isLoading.value = true
                }
                .doAfterTerminate {
                    isLoading.value = false
                }
                .subscribe({
                    listMovie.value = it
                }, { })
    }
}