package moviedb.cleanarchitecture.com.framgia.data.source.remote.network

import io.reactivex.Single
import moviedb.cleanarchitecture.com.framgia.data.model.PersonEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonApi {
    @GET("person/{person_id}")
    fun getDetailPeople(@Path("person_id") idPerson: String): Single<PersonEntity>
}