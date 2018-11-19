package moviedb.cleanarchitecture.com.framgia.domain.model

data class Trailer(
        var id: String? = null,
        var key: String? = null,
        var name: String? = null,
        var site: String? = null,
        var size: String? = null,
        var type: String? = null
) : Model()
