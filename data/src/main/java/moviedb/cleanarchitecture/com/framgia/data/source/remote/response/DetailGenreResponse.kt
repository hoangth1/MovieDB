package moviedb.cleanarchitecture.com.framgia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntity

class DetailGenreResponse(
        @SerializedName("page")
        var page: String? = null,
        @SerializedName("total_pages")
        var totalPage: String? = null,
        @SerializedName("results")
        var listMovie: List<MovieEntity>? = null
)