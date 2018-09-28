package moviedb.cleanarchitecture.com.framgia.domain.model

data class Movie(
        var voteCount: Int? = 0,
        var id: String? = "",
        var video: Boolean? = false,
        var voteAverage: String? = "",
        var title: String? = "",
        var popularity: String? = "",
        var posterPath: String? = "",
        var originalTitle: String? = "",
        var backdropPath: String ?= "",
        var adult: Boolean? = false,
        var overview: String? = "",
        var releaseDate: String? = ""
) : Model()
