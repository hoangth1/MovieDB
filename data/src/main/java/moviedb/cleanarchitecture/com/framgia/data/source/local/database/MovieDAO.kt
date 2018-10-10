package moviedb.cleanarchitecture.com.framgia.data.source.local.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntity

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movie")
    fun getMovies(): Single<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id=:movieId")
    fun getMovie(movieId: String): Single<MovieEntity>

    @Insert
    fun insertMovie(movieEntity: MovieEntity): Long

    @Delete
    fun deleteMovie(movieEntity: MovieEntity): Int
}