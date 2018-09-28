package moviedb.cleanarchitecture.com.framgia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.model.GenreEntity

class GenreResponse(
        @SerializedName("genres")
        var genres: List<GenreEntity>
)
