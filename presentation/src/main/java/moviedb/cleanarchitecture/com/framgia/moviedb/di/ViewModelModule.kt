package moviedb.cleanarchitecture.com.framgia.moviedb.di

import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.moviedb.model.GenreItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.GenreItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.AppSchedulerProvider
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite.FavoriteViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.home.HomeViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { MainViewModel() }
    viewModel { FavoriteViewModel() }
    single { provideCompositeDisposable() }
    single { provideScheduleProvider() }
    single { provideGenreItemMapper() }
    single { provideMovieItemMapper() }
}

fun provideCompositeDisposable() = CompositeDisposable()
fun provideScheduleProvider(): ScheduleProvider = AppSchedulerProvider()
fun provideGenreItemMapper(): GenreItemMapper = GenreItemMapper()
fun provideMovieItemMapper(): MovieItemMapper = MovieItemMapper()
