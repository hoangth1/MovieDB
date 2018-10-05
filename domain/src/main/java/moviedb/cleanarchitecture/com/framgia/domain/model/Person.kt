package moviedb.cleanarchitecture.com.framgia.domain.model

class Person(
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
) : Model()
