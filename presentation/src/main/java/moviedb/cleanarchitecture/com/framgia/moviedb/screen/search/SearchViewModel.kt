package moviedb.cleanarchitecture.com.framgia.moviedb.screen.search

import android.arch.lifecycle.MutableLiveData
import android.graphics.pdf.PdfDocument
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.DeleteFavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.FavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.InsertFavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.SearchMovieUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class SearchViewModel(
        val insertFavoriteUseCase: InsertFavoriteUseCase,
        val deleteFavoriteUseCase: DeleteFavoriteUseCase,
        val favoriteUseCase: FavoriteUseCase,
        val searchMovieUseCase: SearchMovieUseCase,
        val scheduleProvide: ScheduleProvider,
        val compositeDisposable: CompositeDisposable,
        val movieItemMapper: MovieItemMapper
) : BaseViewModel() {
    var stringQuery: String = ""
    val isLoading = MutableLiveData<Boolean>()
    val isLoadMore = MutableLiveData<Boolean>()
    val listMovieResult = MutableLiveData<List<MovieItem>>()
    val tempListMovieItem = mutableListOf<MovieItem>()
    var currentPage = 0;
    fun searchMovies(query: String, page: Int) {
        stringQuery = query
        compositeDisposable.add(
                searchMovieUseCase.createObservable(SearchMovieUseCase.Params(query, page))
                        .subscribeOn(scheduleProvide.io())
                        .observeOn(scheduleProvide.ui())
                        .doOnSubscribe {
                            when {
                                currentPage == 0 -> isLoading.value = true
                                else -> isLoadMore.value = true
                            }

                        }
                        .doAfterTerminate {
                            isLoading.value = false
                            isLoadMore.value = false

                        }
                        .map { listMovie ->
                            listMovie.map { movieItemMapper.mapToPresentation(it) }
                        }
                        .subscribe({
                            addVideoFavorite(it)
                            currentPage = page
                        }, { })
        )
    }

    fun addVideoFavorite(listMovieItem: List<MovieItem>) {
        for (movieItem in listMovieItem) {
            favoriteUseCase.createObservable(FavoriteUseCase.Params(movieItem.id))
                    .subscribeOn(scheduleProvide.io())
                    .observeOn(scheduleProvide.ui())
                    .doFinally {
                        if (movieItem == listMovieItem.last()) {
                            tempListMovieItem.addAll(listMovieItem)
                            val addList: MutableList<MovieItem> = arrayListOf()
                            addList.addAll(tempListMovieItem)
                            listMovieResult.value = addList
                        }
                    }
                    .subscribe({
                        movieItem.isFavorite.set(true)
                    }, { movieItem.isFavorite.set(false) })
        }
    }

    fun addFavorite(movieItem: MovieItem) {
        Observable.create<Long> { emitter ->
            emitter.onNext(insertFavoriteUseCase.createObservable(InsertFavoriteUseCase.Params(movieItemMapper.mapToDomain(movieItem))))
        }.subscribeOn(scheduleProvide.io()).observeOn(scheduleProvide.ui())
                .subscribe({
                    movieItem.isFavorite.set(true)
                }, {
                })
    }

    fun removeFavorite(movieItem: MovieItem) {
        Observable.create<Int> { emitter ->
            emitter.onNext(deleteFavoriteUseCase.createObservable(DeleteFavoriteUseCase.Params(movieItemMapper.mapToDomain(movieItem))))
        }.subscribeOn(scheduleProvide.io()).observeOn(scheduleProvide.ui())
                .subscribe({
                    movieItem.isFavorite.set(false)
                }, {
                })
    }

    fun onLoadMore() {
        searchMovies(stringQuery, currentPage + 1)
    }
}