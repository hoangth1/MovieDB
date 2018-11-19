package moviedb.cleanarchitecture.com.framgia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.model.MovieEntity

class MovieByPersonResponse(
        @SerializedName("cast")
        var listMovie: List<MovieEntity>? = null
)