package moviedb.cleanarchitecture.com.framgia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.model.TrailerEntity

class TrailerResponse(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("results")
        var listTrailer: List<TrailerEntity>? = null
)