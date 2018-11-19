package moviedb.cleanarchitecture.com.framgia.data.source.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}