package moviedb.cleanarchitecture.com.framgia.moviedb.di

import moviedb.cleanarchitecture.com.framgia.domain.repository.GenreRepository
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.genre.GenreUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.NowPlayingUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.PopularUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.TopRatedUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.UpComingUseCase
import org.koin.dsl.module.module

val useCaseModule = module {
    single { provideGenreUseCase(get()) }
    single { provideNowPlayingUseCase(get()) }
    single { providePopularUseCase(get()) }
    single { provideTopRatedUseCase(get()) }
    single { provideUpComingUseCase(get()) }
}

fun provideGenreUseCase(repository: GenreRepository) = GenreUseCase(repository)
fun provideNowPlayingUseCase(repository: MovieRepository) = NowPlayingUseCase(repository)
fun providePopularUseCase(repository: MovieRepository) = PopularUseCase(repository)
fun provideTopRatedUseCase(repository: MovieRepository) = TopRatedUseCase(repository)
fun provideUpComingUseCase(repository: MovieRepository) = UpComingUseCase(repository)
