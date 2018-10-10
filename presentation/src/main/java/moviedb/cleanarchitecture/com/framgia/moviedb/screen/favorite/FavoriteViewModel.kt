package moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.ListFavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class FavoriteViewModel(val listFavoriteUseCase: ListFavoriteUseCase,
                        val compositeDisposable: CompositeDisposable,
                        val scheduleProvider: ScheduleProvider,
                        val movieItemMapper: MovieItemMapper) : BaseViewModel() {
    val listFavoritMovie = MutableLiveData<List<MovieItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val isRefresh = MutableLiveData<Boolean>()
    var isFirsTime = true
    fun getFavoriteMovie() {
        compositeDisposable.add(
                listFavoriteUseCase.createObservable(ListFavoriteUseCase.Params())
                        .subscribeOn(scheduleProvider.io())
                        .observeOn(scheduleProvider.ui())
                        .doOnSubscribe {
                            if (isFirsTime) {
                                isLoading.value = true
                            } else {
                                isRefresh.value = true
                            }

                        }
                        .doAfterTerminate {
                            isLoading.value = false
                            isRefresh.value = false
                        }
                        .map { listMovie ->
                            listMovie.map { movieItemMapper.mapToPresentation(it) }
                        }
                        .subscribe({

                            listFavoritMovie.value = it
                        }, { })
        )
    }

}
