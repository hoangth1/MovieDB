package moviedb.cleanarchitecture.com.framgia.data.di

import moviedb.cleanarchitecture.com.framgia.data.model.GenreEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.source.local.GenreLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.local.MovieLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.GenreRemoteDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.MovieRemoteDatSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.GenreApi
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.MovieApi
import moviedb.cleanarchitecture.com.framgia.data.source.repository.GenreRepositoryImp
import moviedb.cleanarchitecture.com.framgia.data.source.repository.MovieRepositoryImp
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import org.koin.dsl.module.module

val repositoryModule = module(override = true) {
    single { provideGenreRemoteDataSource(get()) }
    single { provideGenreLocalDataSource() }
    single { provideGenreRepository(get(), get(), get()) }
    single { provideMovieRemoteDataSource(get()) }
    single { provideMovieLocalDataSource() }
    single { provideMovieRepository(get(), get(), get()) }
    single { provideGenreEntityMapper() }
    single { provideMovieEntityMapper() }
}

fun provideGenreRepository(remote: GenreRemoteDataSource,
                           local: GenreLocalDataSource,
                           genreEntityMapper: GenreEntityMapper) =
        GenreRepositoryImp(remote, local, genreEntityMapper)

fun provideGenreRemoteDataSource(genreApi: GenreApi) = GenreRemoteDataSource(genreApi)
fun provideGenreLocalDataSource() = GenreLocalDataSource()
fun provideMovieRepository(remote: MovieRemoteDatSource,
                           local: MovieLocalDataSource,
                           movieEntityMapper: MovieEntityMapper): MovieRepository =
        MovieRepositoryImp(remote, local, movieEntityMapper)

fun provideMovieRemoteDataSource(movieApi: MovieApi) = MovieRemoteDatSource(movieApi)
fun provideMovieLocalDataSource() = MovieLocalDataSource()
fun provideGenreEntityMapper() = GenreEntityMapper()
fun provideMovieEntityMapper() = MovieEntityMapper()
