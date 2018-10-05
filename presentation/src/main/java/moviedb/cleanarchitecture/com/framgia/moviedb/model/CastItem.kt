package moviedb.cleanarchitecture.com.framgia.moviedb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import moviedb.cleanarchitecture.com.framgia.domain.model.Cast
import moviedb.cleanarchitecture.com.framgia.domain.model.Model
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ModelItem

@Parcelize
data class CastItem(
        var id: String? = null,
        var character: String? = null,
        var creditId: String? = null,
        var gender: Int? = null,
        var castId: String? = null,
        var name: String? = null,
        var order: Int? = null,
        var profilePath: String? = null
) : ModelItem(), Parcelable

class CastItemMapper : ItemMapper<Cast, CastItem> {
    override fun mapToPresentation(model: Cast): CastItem = CastItem(
            id = model.id,
            character = model.character,
            creditId = model.creditId,
            gender = model.gender,
            castId = model.castId,
            name = model.name,
            order = model.order,
            profilePath = model.profilePath
    )

    override fun mapToDomain(modelItem: CastItem): Cast = Cast(
            id = modelItem.id,
            character = modelItem.character,
            creditId = modelItem.creditId,
            gender = modelItem.gender,
            castId = modelItem.castId,
            name = modelItem.name,
            order = modelItem.order,
            profilePath = modelItem.profilePath
    )
}
