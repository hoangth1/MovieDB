package moviedb.cleanarchitecture.com.framgia.moviedb.model

import moviedb.cleanarchitecture.com.framgia.domain.model.Trailer
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ModelItem

data class TrailerItem(
        var id: String? = null,
        var key: String? = null,
        var name: String? = null,
        var site: String? = null,
        var size: String? = null,
        var type: String? = null
) : ModelItem()

class TrailerItemMapper() : ItemMapper<Trailer, TrailerItem> {
    override fun mapToPresentation(model: Trailer): TrailerItem = TrailerItem(
            id = model.id,
            key = model.key,
            name = model.name,
            site = model.site,
            size = model.size,
            type = model.type
    )

    override fun mapToDomain(modelItem: TrailerItem): Trailer = Trailer(
            id = modelItem.id,
            key = modelItem.key,
            name = modelItem.name,
            site = modelItem.site,
            size = modelItem.size,
            type = modelItem.type
    )
}
