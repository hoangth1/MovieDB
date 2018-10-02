package moviedb.cleanarchitecture.com.framgia.moviedb.screen.home

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import moviedb.cleanarchitecture.com.framgia.domain.usecase.genre.GenreUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.NowPlayingUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.PopularUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.TopRatedUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.UpComingUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.GenreItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.GenreItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class HomeViewModel(
        val genreUseCase: GenreUseCase,
        val nowPlayingUseCase: NowPlayingUseCase,
        val popularUseCase: PopularUseCase,
        val topRatedUseCase: TopRatedUseCase,
        val upComingUseCase: UpComingUseCase,
        val schedulerProvider: ScheduleProvider,
        val compositeDisposable: CompositeDisposable,
        val genreItemMapper: GenreItemMapper,
        val movieItemMapper: MovieItemMapper
) : BaseViewModel() {
    var isLoadGenreSucees = false
    var isLoadNowPlayingSucess = false
    var isLoadPopularSucess = false
    var isLoadTopRatedSucess = false
    var isLoadUpcomingSucess = false
    val isLoading = MutableLiveData<Boolean>()
    val listGenre = MutableLiveData<List<GenreItem>>()
    val listNowPlaying = MutableLiveData<List<MovieItem>>()
    val listPopular = MutableLiveData<List<MovieItem>>()
    val listTopRated = MutableLiveData<List<MovieItem>>()
    val listUpComing = MutableLiveData<List<MovieItem>>()
    fun getData() {
        compositeDisposable.apply {
            isLoading.value = true
            add(genreUseCase.createObservable(GenreUseCase.Params())
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .doOnSubscribe {
                        isLoadGenreSucees = false
                    }
                    .doAfterTerminate {
                        isLoadGenreSucees = true
                        updateLoading()
                    }
                    .map { listGenre ->
                        listGenre.map { genreItemMapper.mapToPresentation(it) }
                    }
                    .subscribe({
                        listGenre.value = it
                    }, { }))
            add(nowPlayingUseCase.createObservable(NowPlayingUseCase.Params(1))
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .doOnSubscribe {
                        isLoadNowPlayingSucess = false
                    }
                    .doAfterTerminate {
                        isLoadNowPlayingSucess = true
                        updateLoading()
                    }
                    .map { listMovie ->
                        listMovie.map { movieItemMapper.mapToPresentation(it) }

                    }
                    .subscribe({
                        listNowPlaying.value = it
                    }, { }))
            add(popularUseCase.createObservable(PopularUseCase.Params(1))
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .doOnSubscribe {
                        isLoadPopularSucess = false
                    }
                    .doAfterTerminate {
                        isLoadPopularSucess = true
                        updateLoading()
                    }
                    .map { listMovie ->
                        listMovie.map { movieItemMapper.mapToPresentation(it) }

                    }
                    .subscribe({
                        listPopular.value = it
                    }, { }))
            add(topRatedUseCase.createObservable(TopRatedUseCase.Params(1))
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .doOnSubscribe {
                        isLoadTopRatedSucess = false
                    }
                    .doAfterTerminate {
                        isLoadTopRatedSucess = true
                        updateLoading()
                    }
                    .map { listMovie ->
                        listMovie.map { movieItemMapper.mapToPresentation(it) }

                    }
                    .subscribe({
                        listTopRated.value = it
                    }, { }))
            add(upComingUseCase.createObservable(UpComingUseCase.Params(1))
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .doOnSubscribe {
                        isLoadUpcomingSucess = false
                    }
                    .doAfterTerminate {
                        isLoadUpcomingSucess = true
                        updateLoading()
                    }
                    .map { listMovie ->
                        listMovie.map { movieItemMapper.mapToPresentation(it) }

                    }
                    .subscribe({
                        listUpComing.value = it
                    }, { }))
        }
    }

    fun updateLoading() {
        isLoading.value = !(isLoadGenreSucees && isLoadNowPlayingSucess
                && isLoadPopularSucess && isLoadTopRatedSucess && isLoadUpcomingSucess)
    }
}
