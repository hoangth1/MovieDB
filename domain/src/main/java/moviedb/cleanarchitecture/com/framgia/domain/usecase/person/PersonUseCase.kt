package moviedb.cleanarchitecture.com.framgia.domain.usecase.person

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.domain.model.Person
import moviedb.cleanarchitecture.com.framgia.domain.repository.PersonRepository
import moviedb.cleanarchitecture.com.framgia.domain.usecase.UseCase

class PersonUseCase(private val personRepository: PersonRepository) : UseCase<PersonUseCase.Params, Single<Person>>() {
    override fun createObservable(params: Params?): Single<Person> {
        params?.let { return personRepository.getPerson(it.idPerson) }
        return Single.error(Throwable(""))
    }

    override fun onCleared() {
    }

    data class Params(val idPerson: String)
}