package moviedb.cleanarchitecture.com.framgia.moviedb.screen.home

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.NowPlayingUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.PopularUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.TopRatedUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.UpComingUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.AppSchedulerProvider

class HomeViewModel(
        val nowPlayingUseCase: NowPlayingUseCase,
        val popularUseCase: PopularUseCase,
        val topRatedUseCase: TopRatedUseCase,
        val upComingUseCase: UpComingUseCase,
        val schedulerProvider: AppSchedulerProvider,
        val compositeDisposable: CompositeDisposable
) : BaseViewModel() {
    fun getGenres() {

        nowPlayingUseCase.createObservable(NowPlayingUseCase.Params(1)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(Consumer {
            Log.d("kiemtra", it.size.toString())
        })
    }
}