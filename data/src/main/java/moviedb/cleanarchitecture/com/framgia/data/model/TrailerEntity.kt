package moviedb.cleanarchitecture.com.framgia.data.model

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.base.EntityMapper
import moviedb.cleanarchitecture.com.framgia.data.base.ModelEntity
import moviedb.cleanarchitecture.com.framgia.domain.model.Trailer

class TrailerEntity(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("key")
        var key: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("site")
        var site: String? = null,
        @SerializedName("size")
        var size: String? = null,
        @SerializedName("type")
        var type: String? = null
) : ModelEntity()

class TrailerEntityMapper : EntityMapper<Trailer, TrailerEntity> {
    override fun mapToDomain(entity: TrailerEntity): Trailer = Trailer(
            id = entity.id,
            key = entity.key,
            name = entity.name,
            site = entity.site,
            size = entity.size,
            type = entity.type
    )

    override fun mapToEntity(model: Trailer): TrailerEntity = TrailerEntity(
            id = model.id,
            key = model.key,
            name = model.name,
            site = model.site,
            size = model.size,
            type = model.type
    )
}