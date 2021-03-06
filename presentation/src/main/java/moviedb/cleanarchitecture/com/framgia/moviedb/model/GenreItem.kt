package moviedb.cleanarchitecture.com.framgia.moviedb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import moviedb.cleanarchitecture.com.framgia.domain.model.Genre
import moviedb.cleanarchitecture.com.framgia.domain.model.Model
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ModelItem
@Parcelize
data class GenreItem(
        var id: String? = null,
        var name: String? = null
) : ModelItem(), Parcelable

class GenreItemMapper : ItemMapper<Genre, GenreItem> {
    override fun mapToPresentation(model: Genre) = GenreItem(model.id, model.name)

    override fun mapToDomain(modelItem: GenreItem) = Genre(modelItem.id, modelItem.name)
}
