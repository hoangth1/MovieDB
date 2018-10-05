package moviedb.cleanarchitecture.com.framgia.data.di

import moviedb.cleanarchitecture.com.framgia.data.model.*
import moviedb.cleanarchitecture.com.framgia.data.source.local.GenreLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.local.MovieLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.local.PersonLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.GenreRemoteDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.MovieRemoteDatSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.PersonRemoteDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.GenreApi
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.MovieApi
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.PersonApi
import moviedb.cleanarchitecture.com.framgia.data.source.repository.GenreRepositoryImp
import moviedb.cleanarchitecture.com.framgia.data.source.repository.MovieRepositoryImp
import moviedb.cleanarchitecture.com.framgia.data.source.repository.PersonRepositoryImp
import moviedb.cleanarchitecture.com.framgia.domain.repository.GenreRepository
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository
import moviedb.cleanarchitecture.com.framgia.domain.repository.PersonRepository
import org.koin.dsl.module.module

val repositoryModule = module(override = true) {
    single { provideGenreRemoteDataSource(get()) }
    single { provideGenreLocalDataSource() }
    single { provideGenreRepository(get(), get(), get()) }
    single { provideMovieRemoteDataSource(get()) }
    single { provideMovieLocalDataSource() }
    single { provideMovieRepository(get(), get(), get(), get(), get()) }
    single { provideGenreEntityMapper() }
    single { provideMovieEntityMapper() }
    single { provideCastEntityMapper() }
    single { provideTrailerEntityMapper() }
    single { providePersonRemoteDataSource(get()) }
    single { providePersonLocalDataSource() }
    single { providePersonRepository(get(), get(), get()) }
    single { proviePersonEntityMapper() }

}

fun provideGenreRepository(remote: GenreRemoteDataSource,
                           local: GenreLocalDataSource,
                           genreEntityMapper: GenreEntityMapper): GenreRepository =
        GenreRepositoryImp(remote, local, genreEntityMapper)

fun provideGenreRemoteDataSource(genreApi: GenreApi) = GenreRemoteDataSource(genreApi)
fun provideGenreLocalDataSource() = GenreLocalDataSource()
fun provideMovieRepository(remote: MovieRemoteDatSource,
                           local: MovieLocalDataSource,
                           movieEntityMapper: MovieEntityMapper,
                           castEntityMapper: CastEntityMapper,
                           trailerEntityMapper: TrailerEntityMapper): MovieRepository =
        MovieRepositoryImp(remote, local, movieEntityMapper, castEntityMapper, trailerEntityMapper)

fun providePersonRemoteDataSource(personApi: PersonApi) = PersonRemoteDataSource(personApi)
fun providePersonLocalDataSource() = PersonLocalDataSource()
fun providePersonRepository(remote: PersonRemoteDataSource,
                            local: PersonLocalDataSource,
                            personEntityMapper: PersonEntityMapper): PersonRepository =
        PersonRepositoryImp(remote, local, personEntityMapper)


fun provideMovieRemoteDataSource(movieApi: MovieApi) = MovieRemoteDatSource(movieApi)
fun provideMovieLocalDataSource() = MovieLocalDataSource()
fun provideGenreEntityMapper() = GenreEntityMapper()
fun provideMovieEntityMapper() = MovieEntityMapper()
fun provideCastEntityMapper() = CastEntityMapper()
fun provideTrailerEntityMapper() = TrailerEntityMapper()
fun proviePersonEntityMapper() = PersonEntityMapper()
