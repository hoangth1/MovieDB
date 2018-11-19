package moviedb.cleanarchitecture.com.framgia.domain.model

data class Movie(
        var voteCount: Int? = 0,
        var id: String = "",
        var video: Boolean? = false,
        var voteAverage: String? = null,
        var title: String? = "",
        var popularity: String? = null,
        var posterPath: String? = null,
        var originalTitle: String? = null,
        var backdropPath: String ?= null,
        var adult: Boolean? = false,
        var overview: String? = null,
        var releaseDate: String? = null
) : Model()
