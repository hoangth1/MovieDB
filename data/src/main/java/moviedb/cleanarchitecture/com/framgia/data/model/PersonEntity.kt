package moviedb.cleanarchitecture.com.framgia.data.model

import com.google.gson.annotations.SerializedName
import moviedb.cleanarchitecture.com.framgia.data.base.EntityMapper
import moviedb.cleanarchitecture.com.framgia.data.base.ModelEntity
import moviedb.cleanarchitecture.com.framgia.domain.model.Person

class PersonEntity(
        @SerializedName("birthday")
        var birthday: String? = null,
        @SerializedName("known_for_department")
        var knowForDepartment: String? = null,
        @SerializedName("deathday")
        var deatday: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("gender")
        var gender: Int? = null,
        @SerializedName("biography")
        var biography: String? = null,
        @SerializedName("popularity")
        var popularity: String? = null,
        @SerializedName("place_of_birth")
        var placeOfBirthDay: String? = null,
        @SerializedName("profile_path")
        var profilePath: String? = null,
        @SerializedName("adult")
        var adult: Boolean? = null,
        @SerializedName("imdb_id")
        var imdbId: String? = null,
        @SerializedName("homepage")
        var homePage: String? = null
) : ModelEntity()

class PersonEntityMapper : EntityMapper<Person, PersonEntity> {
    override fun mapToDomain(entity: PersonEntity): Person = Person(
            birthday = entity.birthday,
            knowForDepartment = entity.knowForDepartment,
            deatday = entity.deatday,
            id = entity.id,
            name = entity.name,
            gender = entity.gender,
            biography = entity.biography,
            popularity = entity.popularity,
            placeOfBirthDay = entity.placeOfBirthDay,
            profilePath = entity.profilePath,
            adult = entity.adult,
            imdbId = entity.imdbId,
            homePage = entity.homePage
    )

    override fun mapToEntity(model: Person): PersonEntity = PersonEntity(
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
}