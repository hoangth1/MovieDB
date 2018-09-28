package moviedb.cleanarchitecture.com.framgia.data.base

import moviedb.cleanarchitecture.com.framgia.domain.model.Model

interface EntityMapper<M : Model, E : ModelEntity> {
    fun mapToDomain(entity: E): M
    fun mapToEntity(model: M): E
}
