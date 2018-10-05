package moviedb.cleanarchitecture.com.framgia.moviedb.di

import moviedb.cleanarchitecture.com.framgia.domain.repository.GenreRepository
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.repository.PersonRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.genre.GenreUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.*
import moviedb.cleanarchitecture.com.framgia.domain.usecase.person.PersonUseCase
import org.koin.dsl.module.module

val useCaseModule = module {
    single { provideGenreUseCase(get()) }
    single { provideNowPlayingUseCase(get()) }
    single { providePopularUseCase(get()) }
    single { provideTopRatedUseCase(get()) }
    single { provideUpComingUseCase(get()) }
    single { provideCastUseCase(get()) }
    single { provideDetailGenreUseCase(get()) }
    single { provideSearchMovieUseCase(get()) }
    single { provideTrailerUseCase(get()) }
    single { providePersonUseCase(get()) }
    single { provideMovieByPersonUseCase(get()) }
}

fun provideGenreUseCase(repository: GenreRepository) = GenreUseCase(repository)
fun provideNowPlayingUseCase(repository: MovieRepository) = NowPlayingUseCase(repository)
fun providePopularUseCase(repository: MovieRepository) = PopularUseCase(repository)
fun provideTopRatedUseCase(repository: MovieRepository) = TopRatedUseCase(repository)
fun provideUpComingUseCase(repository: MovieRepository) = UpComingUseCase(repository)
fun provideCastUseCase(repository: MovieRepository) = CastUseCase(repository)
fun provideDetailGenreUseCase(repository: MovieRepository) = DetailGenreUseCase(repository)
fun provideSearchMovieUseCase(repository: MovieRepository) = SearchMovieUseCase(repository)
fun provideTrailerUseCase(repository: MovieRepository) = TrailerUseCase(repository)
fun providePersonUseCase(repository: PersonRepository) = PersonUseCase(repository)
fun provideMovieByPersonUseCase(repository: MovieRepository) = MovieByPersonUseCase(repository)
