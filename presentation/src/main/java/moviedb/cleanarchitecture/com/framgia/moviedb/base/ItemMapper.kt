package moviedb.cleanarchitecture.com.framgia.moviedb.base

import moviedb.cleanarchitecture.com.framgia.domain.model.Model

interface ItemMapper<M : Model, MI : ModelItem> {
    fun mapToPresentation(model: M): ModelItem

    fun mapToDomain(modelItem: MI): Model
}
