package moviedb.cleanarchitecture.com.framgia.data.source.repository

import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.source.local.MovieLocalDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.MovieRemoteDatSource
import moviedb.cleanarchitecture.com.framgia.domain.repository.MovieRepository

class MovieRepositoryImp(val remote: MovieRemoteDatSource,
                         val local: MovieLocalDataSource,
                         private val movieEntityMapper: MovieEntityMapper) : MovieRepository {
    override fun getListNowPlaying(page: Int) = remote.getListNowPlaying(page).map { response ->
        response.listMovie.map { movieEntityMapper.mapToDomain(it) }
    }

    override fun getListPopular(page: Int) = remote.getListPopular(page).map { response ->
        response.listMovie.map { movieEntityMapper.mapToDomain(it) }
    }

    override fun getListTopRated(page: Int) = remote.getListTopRated(page).map { response ->
        response.listMovie.map { movieEntityMapper.mapToDomain(it) }
    }

    override fun getListUpComing(page: Int) = remote.getListUpComing(page).map { response ->
        response.listMovie.map { movieEntityMapper.mapToDomain(it) }
    }
}
