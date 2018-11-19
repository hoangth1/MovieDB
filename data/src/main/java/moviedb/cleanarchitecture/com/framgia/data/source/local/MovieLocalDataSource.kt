package moviedb.cleanarchitecture.com.framgia.data.source.local

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntity
import moviedb.cleanarchitecture.com.framgia.data.source.MovieDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.local.database.MovieDAO

class MovieLocalDataSource(val movieDAO: MovieDAO) : MovieDataSource.Local {
    override fun getMovie(idMovie: String): Single<MovieEntity> = movieDAO.getMovie(idMovie)
    override fun insertMovie(movieEntity: MovieEntity): Long = movieDAO.insertMovie(movieEntity)
    override fun deleteMovie(movieEntity: MovieEntity): Int = movieDAO.deleteMovie(movieEntity)
    override fun getMovies(): Single<List<MovieEntity>> = movieDAO.getMovies()
}
