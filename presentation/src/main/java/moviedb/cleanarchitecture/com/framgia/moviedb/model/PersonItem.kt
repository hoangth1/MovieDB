package moviedb.cleanarchitecture.com.framgia.moviedb.model

import moviedb.cleanarchitecture.com.framgia.domain.model.Person
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.base.ModelItem

class PersonItem(
        var birthday: String? = null,
        var knowForDepartment: String? = null,
        var deatday: String? = null,
        var id: String? = null,
        var name: String? = null,
        var gender: Int? = null,
        var biography: String? = null,
        var popularity: String? = null,
        var placeOfBirthDay: String? = null,
        var profilePath: String? = null,
        var adult: Boolean? = null,
        var imdbId: String? = null,
        var homePage: String? = null
) : ModelItem()

class PersonItemMapper : ItemMapper<Person, PersonItem> {
    override fun mapToPresentation(model: Person) = PersonItem(
            birthday = model.birthday,
            knowForDepartment = model.knowForDepartment,
            deatday = model.deatday,
            id = model.id,
            name = model.name,
            gender = model.gender,
            biography = model.biography,
            popularity = model.popularity,
            placeOfBirthDay = model.placeOfBirthDay,
            profilePath = model.profilePath,
            adult = model.adult,
            imdbId = model.imdbId,
            homePage = model.homePage
    )

    override fun mapToDomain(modelItem: PersonItem) = Person(
            birthday = modelItem.birthday,
            knowForDepartment = modelItem.knowForDepartment,
            deatday = modelItem.deatday,
            id = modelItem.id,
            name = modelItem.name,
            gender = modelItem.gender,
            biography = modelItem.biography,
            popularity = modelItem.popularity,
            placeOfBirthDay = modelItem.placeOfBirthDay,
            profilePath = modelItem.profilePath,
            adult = modelItem.adult,
            imdbId = modelItem.imdbId,
            homePage = modelItem.homePage
    )
}