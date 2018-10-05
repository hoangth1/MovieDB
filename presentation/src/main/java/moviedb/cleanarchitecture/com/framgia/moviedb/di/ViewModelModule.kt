package moviedb.cleanarchitecture.com.framgia.moviedb.di

import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.moviedb.model.*
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.AppSchedulerProvider
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail.DetailViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailcast.DetailCastViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailgenre.DetailGenreViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.favorite.FavoriteViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.home.HomeViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.main.MainViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.playtrailer.PlayTrailerViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.screen.search.SearchViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { MainViewModel() }
    viewModel { FavoriteViewModel() }
    viewModel { DetailViewModel(get(), get(), get(), get()) }
    viewModel { DetailGenreViewModel(get(), get(), get(), get()) }
    viewModel { SearchViewModel(get(), get(), get(), get()) }
    viewModel { PlayTrailerViewModel(get(), get(), get(), get()) }
    viewModel { DetailCastViewModel(get(), get(), get(), get(), get()) }
    single { provideCompositeDisposable() }
    single { provideScheduleProvider() }
    single { provideGenreItemMapper() }
    single { provideMovieItemMapper() }
    single { provideCastItemMapper() }
    single { provideTrailerItemMapper() }
    single { providePersonItemMapper() }
}

fun provideCompositeDisposable() = CompositeDisposable()
fun provideScheduleProvider(): ScheduleProvider = AppSchedulerProvider()
fun provideGenreItemMapper(): GenreItemMapper = GenreItemMapper()
fun provideMovieItemMapper(): MovieItemMapper = MovieItemMapper()
fun provideCastItemMapper(): CastItemMapper = CastItemMapper()
fun provideTrailerItemMapper(): TrailerItemMapper = TrailerItemMapper()
fun providePersonItemMapper(): PersonItemMapper = PersonItemMapper()
