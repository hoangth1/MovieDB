package moviedb.cleanarchitecture.com.framgia.data.model

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.base.ModelEntity

class GenreEntity(
        @SerializedName("id")
        var id: String = "",
        @SerializedName("name")
        var name: String = ""
) : ModelEntity()
