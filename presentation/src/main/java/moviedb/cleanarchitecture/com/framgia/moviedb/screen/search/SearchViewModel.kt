package moviedb.cleanarchitecture.com.framgia.moviedb.screen.search

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.SearchMovieUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class SearchViewModel(
        val searchMovieUseCase: SearchMovieUseCase,
        val scheduleProvide: ScheduleProvider,
        val compositeDisposable: CompositeDisposable,
        val movieItemMapper: MovieItemMapper
) : BaseViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val listMovieResult = MutableLiveData<List<MovieItem>>()
    fun searchMovies(query: String) {
        searchMovieUseCase.createObservable(SearchMovieUseCase.Params(query, 1))
                .subscribeOn(scheduleProvide.io())
                .observeOn(scheduleProvide.ui())
                .doOnSubscribe {
                    isLoading.value = true
                }
                .doAfterTerminate {
                    isLoading.value = false
                }
                .map { listMovie ->
                    listMovie.map { movieItemMapper.mapToPresentation(it) }
                }
                .subscribe({
                    listMovieResult.value = it
                }, { })
    }
}