package moviedb.cleanarchitecture.com.framgia.data.di

import moviedb.cleanarchitecture.com.framgia.data.source.local.GenreLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.local.MovieLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.GenreRemoteDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.MovieRemoteDatSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.GenreApi
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.MovieApi
import moviedb.cleanarchitecture.com.framgia.data.source.repository.GenreRepository
import moviedb.cleanarchitecture.com.framgia.data.source.repository.MovieRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    single { provideGenreRemoteDataSource(get()) }
    single { provideGenreLocalDataSource() }
    single { provideGenreRepository(get(), get()) }
    single { provideMovieRemoteDataSource(get()) }
    single { provideMovieLocalDataSource() }
    single { provideMovieRepository(get(), get()) }
}

fun provideGenreRepository(remote: GenreRemoteDataSource,
                           local: GenreLocalDataSource) = GenreRepository(remote, local)

fun provideGenreRemoteDataSource(genreApi: GenreApi) = GenreRemoteDataSource(genreApi)
fun provideGenreLocalDataSource() = GenreLocalDataSource()
fun provideMovieRepository(remote: MovieRemoteDatSource,
                           local: MovieLocalDataSource) = MovieRepository(remote, local)

fun provideMovieRemoteDataSource(movieApi: MovieApi) = MovieRemoteDatSource(movieApi)
fun provideMovieLocalDataSource() = MovieLocalDataSource()
