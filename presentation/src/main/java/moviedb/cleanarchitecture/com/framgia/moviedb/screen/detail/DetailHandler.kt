package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detail

import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem

interface DetailHandler {
    fun onClickTrailer(movieItem: MovieItem)
    fun onFavoriteClick()
    fun onBackClick()
}
