package moviedb.cleanarchitecture.com.framgia.moviedb.model

import moviedb.cleanarchitecture.com.framgia.domain.model.Model
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ModelItem

data class MovieItem(
        var voteCount: Int? = 0,
        var id: String? = "",
        var video: Boolean? = false,
        var voteAverage: String? = "",
        var title: String? = "",
        var popularity: String? = "",
        var posterPath: String? = "",
        var originalTitle: String? = "",
        var backdropPath: String? = "",
        var adult: Boolean? = false,
        var overview: String? = "",
        var releaseDate: String? = ""
) : ModelItem()

class MovieItemMapper : ItemMapper<Movie, MovieItem> {
    override fun mapToPresentation(model: Movie): ModelItem = MovieItem(
            voteCount = model.voteCount,
            id = model.id,
            video = model.video,
            voteAverage = model.voteAverage,
            title = model.title,
            popularity = model.popularity,
            posterPath = model.posterPath,
            originalTitle = model.originalTitle,
            backdropPath = model.backdropPath,
            adult = model.adult,
            overview = model.overview,
            releaseDate = model.releaseDate
    )

    override fun mapToDomain(modelItem: MovieItem) = Movie(
            voteCount = modelItem.voteCount,
            id = modelItem.id,
            video = modelItem.video,
            voteAverage = modelItem.voteAverage,
            title = modelItem.title,
            popularity = modelItem.popularity,
            posterPath = modelItem.posterPath,
            originalTitle = modelItem.originalTitle,
            backdropPath = modelItem.backdropPath,
            adult = modelItem.adult,
            overview = modelItem.overview,
            releaseDate = modelItem.releaseDate
    )
}