package moviedb.cleanarchitecture.com.framgia.data.model

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.base.EntityMapper
import moviedb.cleanarchitecture.com.framgia.data.base.ModelEntity
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast

class CastEntity(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("character")
        var character: String? = null,
        @SerializedName("credit_id")
        var creditId: String? = null,
        @SerializedName("gender")
        var gender: Int? = null,
        @SerializedName("cast_id")
        var castId: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("order")
        var order: Int? = null,
        @SerializedName("profile_path")
        var profilePath: String? = null
) : ModelEntity()

class CastEntityMapper() : EntityMapper<Cast, CastEntity> {
    override fun mapToDomain(entity: CastEntity): Cast = Cast(
            id = entity.id,
            character = entity.character,
            creditId = entity.creditId,
            gender = entity.gender,
            castId = entity.castId,
            name = entity.name,
            order = entity.order,
            profilePath = entity.profilePath
    )

    override fun mapToEntity(model: Cast): CastEntity = CastEntity(
            id = model.id,
            character = model.character,
            creditId = model.creditId,
            gender = model.gender,
            castId = model.castId,
            name = model.name,
            order = model.order,
            profilePath = model.profilePath
    )
}
