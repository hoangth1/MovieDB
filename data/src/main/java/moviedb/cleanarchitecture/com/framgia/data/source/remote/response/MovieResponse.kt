package moviedb.cleanarchitecture.com.framgia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntity

class MovieResponse(
        @SerializedName("results")
        var listMovie: List<MovieEntity>,
        @SerializedName("page")
        var page: Int = 0,
        @SerializedName("total_results")
        var totalResult: Int = 0,
        @SerializedName("total_pages")
        var totalPage: Int = 0
)
