package moviedb.cleanarchitecture.com.framgia.data.source.remote

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.PersonEntity
import moviedb.cleanarchitecture.com.framgia.data.source.PersonDataSource
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.PersonApi
import moviedb.cleanarchitecture.com.framgia.domain.model.Person

class PersonRemoteDataSource(val personApi: PersonApi) : PersonDataSource.Remote {
    override fun getPerson(idPerson: String): Single<PersonEntity> = personApi.getDetailPeople(idPerson)
}