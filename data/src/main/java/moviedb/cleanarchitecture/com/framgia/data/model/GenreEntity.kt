package moviedb.cleanarchitecture.com.framgia.data.model

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.base.EntityMapper
import moviedb.cleanarchitecture.com.framgia.data.base.ModelEntity
import moviedb.cleanarchitecture.com.framgia.domain.model.Genre

class GenreEntity(
        @SerializedName("id")
        var id: String = "",
        @SerializedName("name")
        var name: String = ""
) : ModelEntity()

class GenreEntityMapper : EntityMapper<Genre, GenreEntity> {
    override fun mapToEntity(model: Genre) = GenreEntity(model.id, model.name)

    override fun mapToDomain(entity: GenreEntity) = Genre(entity.id, entity.name)

}
