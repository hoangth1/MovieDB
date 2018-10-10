package moviedb.cleanarchitecture.com.framgia.data.source

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.PersonEntity
import moviedb.cleanarchitecture.com.framgia.domain.model.Person

interface PersonDataSource {
    interface Remote {
        fun getPerson(idPerson: String): Single<PersonEntity>
    }

    interface Local {

    }
}