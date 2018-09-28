package moviedb.cleanarchitecture.com.framgia.data.model

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.base.ModelEntity

class MovieEntity(
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        @SerializedName("id")
        var id: String = "",
        @SerializedName("video")
        var video: Boolean = false,
        @SerializedName("vote_average")
        var voteAverage: String = "",
        @SerializedName("title")
        var title: String = "",
        @SerializedName("popularity")
        var popularity: String = "",
        @SerializedName("poster_path")
        var posterPath: String = "",
        @SerializedName("original_title")
        var originalTitle: String = "",
        @SerializedName("backdrop_path")
        var backdropPath: String = "",
        @SerializedName("adult")
        var adult: Boolean = false,
        @SerializedName("overview")
        var overview: String = "",
        @SerializedName("release_date")
        var releaseDate: String = ""
) : ModelEntity()
