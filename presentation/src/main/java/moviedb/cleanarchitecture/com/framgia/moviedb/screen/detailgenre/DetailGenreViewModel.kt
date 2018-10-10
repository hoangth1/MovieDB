package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailgenre

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.DeleteFavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.DetailGenreUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.FavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.InsertFavoriteUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class DetailGenreViewModel(
        val inserFavoriteUseCase: InsertFavoriteUseCase,
        val deleteFavoriteUseCase: DeleteFavoriteUseCase,
        val favoriteUseCase: FavoriteUseCase,
        val detailGenreUseCase: DetailGenreUseCase,
        val scheduleProvider: ScheduleProvider,
        val compositeDisposable: CompositeDisposable,
        val movieItemMapper: MovieItemMapper
) : BaseViewModel() {
    val listMovie = MutableLiveData<List<MovieItem>>()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    var currentPage: Int = 0
    var totalPage: Int = 0
    var genreId: String = ""
    var tempListMovie: MutableList<MovieItem> = arrayListOf()
    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }
    val isRefresh = MutableLiveData<Boolean>().apply { value = false }
    fun getMovies(genreId: String, page: Int) {
        this.genreId = genreId
        compositeDisposable.addAll(
                detailGenreUseCase.createObservable(DetailGenreUseCase.Params(genreId, page))
                        .subscribeOn(scheduleProvider.io())
                        .observeOn(scheduleProvider.ui())
                        .map { movieInfor ->
                            movieInfor.totalPage?.let { totalPage = it }
                            movieInfor.listMovie?.map { movieItemMapper.mapToPresentation(it) }
                        }
                        .doOnSubscribe {
                            when {
                                currentPage == 0 -> isLoading.value = true
                                page == currentPage && currentPage != 0 -> isRefresh.value = true
                                page - 1 == currentPage && currentPage != 0 -> isLoadMore.value = true

                            }
                        }
                        .doAfterTerminate {
                            isLoading.value = false
                            isLoadMore.value = false
                            isRefresh.value = false
                        }
                        .subscribe({
                            addVideoFavorite(it ?: return@subscribe)
                            currentPage++
                        }, { })
        )
    }

    fun addFavorite(movieItem: MovieItem) {
        Observable.create<Long> { emitter ->
            emitter.onNext(inserFavoriteUseCase.createObservable(InsertFavoriteUseCase.Params(movieItemMapper.mapToDomain(movieItem))))
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

    fun addVideoFavorite(listMovieItem: List<MovieItem>) {
        for (movieItem in listMovieItem) {
            favoriteUseCase.createObservable(FavoriteUseCase.Params(movieItem.id))
                    .subscribeOn(scheduleProvider.io())
                    .observeOn(scheduleProvider.ui())
                    .doFinally {
                        if (movieItem == listMovieItem.last()) {
                            tempListMovie.addAll(listMovieItem)
                            val addList: MutableList<MovieItem> = arrayListOf()
                            addList.addAll(tempListMovie)
                            listMovie.value = addList
                        }
                    }
                    .subscribe({
                        movieItem.isFavorite.set(true)
                    }, { movieItem.isFavorite.set(false) })
        }
    }

    fun onLoadMore() {
        if (currentPage == totalPage) return
        getMovies(genreId, currentPage + 1)
    }

    fun referes() {
        getMovies(genreId, 1)
        tempListMovie.clear()
    }
}