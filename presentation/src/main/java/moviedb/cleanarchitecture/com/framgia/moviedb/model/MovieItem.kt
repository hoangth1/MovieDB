package moviedb.cleanarchitecture.com.framgia.moviedb.model

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import moviedb.cleanarchitecture.com.framgia.domain.model.Model
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ModelItem

@Parcelize
data class MovieItem(
        var voteCount: Int? = null,
        var id: String = "",
        var video: Boolean? = false,
        var voteAverage: String? = null,
        var title: String? = null,
        var popularity: String? = null,
        var posterPath: String? = null,
        var originalTitle: String? = null,
        var backdropPath: String? = null,
        var adult: Boolean? = false,
        var overview: String? = null,
        var releaseDate: String? = null
) : ModelItem(), Parcelable {
    var isFavorite = ObservableField<Boolean>()
}

class MovieItemMapper : ItemMapper<Movie, MovieItem> {
    override fun mapToPresentation(model: Movie): MovieItem = MovieItem(
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
