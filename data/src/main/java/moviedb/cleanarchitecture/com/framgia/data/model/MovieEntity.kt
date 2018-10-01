package moviedb.cleanarchitecture.com.framgia.data.model

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.base.EntityMapper
import moviedb.cleanarchitecture.com.framgia.data.base.ModelEntity
import moviedb.cleanarchitecture.com.framgia.domain.model.Movie

class MovieEntity(
        @SerializedName("vote_count")
        var voteCount: Int? = 0,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("video")
        var video: Boolean? = null,
        @SerializedName("vote_average")
        var voteAverage: String? = null,
        @SerializedName("title")
        var title: String? = null,
        @SerializedName("popularity")
        var popularity: String? = null,
        @SerializedName("poster_path")
        var posterPath: String? = null,
        @SerializedName("original_title")
        var originalTitle: String? = null,
        @SerializedName("backdrop_path")
        var backdropPath: String ?= null,
        @SerializedName("adult")
        var adult: Boolean? = false,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null
) : ModelEntity()

class MovieEntityMapper : EntityMapper<Movie, MovieEntity> {
    override fun mapToDomain(entity: MovieEntity) = Movie(
            voteCount = entity.voteCount,
            id = entity.id,
            video = entity.video,
            voteAverage = entity.voteAverage,
            title = entity.title,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            originalTitle = entity.originalTitle,
            backdropPath = entity.backdropPath,
            adult = entity.adult,
            overview = entity.overview,
            releaseDate = entity.releaseDate
    )

    override fun mapToEntity(model: Movie): MovieEntity = MovieEntity(
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
}
