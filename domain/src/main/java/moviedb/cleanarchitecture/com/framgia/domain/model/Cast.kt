package moviedb.cleanarchitecture.com.framgia.domain.model

data class Cast(
        var id: String? = null,
        var character: String? = null,
        var creditId: String? = null,
        var gender: Int? = null,
        var castId: String? = null,
        var name: String? = null,
        var order: Int? = null,
        var profilePath: String? = null
) : Model()
