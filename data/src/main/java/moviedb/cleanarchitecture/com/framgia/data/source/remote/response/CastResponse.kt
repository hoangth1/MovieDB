package moviedb.cleanarchitecture.com.framgia.data.source.remote.response

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.model.CastEntity

class CastResponse(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("cast")
        var listCasts: List<CastEntity>? = null
)
