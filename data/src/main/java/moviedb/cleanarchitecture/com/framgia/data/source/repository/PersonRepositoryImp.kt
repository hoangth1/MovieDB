package moviedb.cleanarchitecture.com.framgia.data.source.repository

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.PersonEntityMapper
import moviedb.cleanarchitecture.com.framgia.data.source.PersonDataSource
import moviedb.cleanarchitecture.com.framgia.domain.model.Person
import moviedb.cleanarchitecture.com.framgia.domain.repository.PersonRepository

class PersonRepositoryImp(val remote: PersonDataSource.Remote,
                          val local: PersonDataSource.Local,
                          private val personEntityMapper: PersonEntityMapper) : PersonRepository {
    override fun getPerson(idPeople: String): Single<Person> = remote.getPerson(idPeople)
            .map {
                personEntityMapper.mapToDomain(it)
            }
}