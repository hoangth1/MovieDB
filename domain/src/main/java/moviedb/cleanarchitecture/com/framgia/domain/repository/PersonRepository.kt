package moviedb.cleanarchitecture.com.framgia.domain.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Person

interface PersonRepository {
    fun getPerson(idPeople: String): Single<Person>
}