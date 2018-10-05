package moviedb.cleanarchitecture.com.framgia.moviedb.screen.detailcast

import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import moviedb.cleanarchitecture.com.framgia.domain.usecase.movie.MovieByPersonUseCase
import moviedb.cleanarchitecture.com.framgia.domain.usecase.person.PersonUseCase
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.MovieItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.model.PersonItem
import moviedb.cleanarchitecture.com.framgia.moviedb.model.PersonItemMapper
import moviedb.cleanarchitecture.com.framgia.moviedb.rx.ScheduleProvider

class DetailCastViewModel(
        val personUseCase: PersonUseCase,
        val movieByPersonUseCase: MovieByPersonUseCase,
        val scheduleProvider: ScheduleProvider,
        val compositeDisposable: CompositeDisposable,
        val personItemMapper: PersonItemMapper,
        val movieItemMapper: MovieItemMapper) : BaseViewModel() {
    val listMovie = MutableLiveData<List<MovieItem>>()
    val person = MutableLiveData<PersonItem>()
    val isLoading = MutableLiveData<Boolean>()
    var isLoadPersonSucess = false
    var isLoadRelatedMovieSuccess = false
    fun getPerson(personId: String) {
        compositeDisposable.apply {
            add(
                    personUseCase.createObservable(PersonUseCase.Params(personId))
                            .subscribeOn(scheduleProvider.io())
                            .observeOn(scheduleProvider.ui())
                            .doOnSubscribe {
                                isLoadPersonSucess = false
                            }
                            .doAfterTerminate {
                                isLoadPersonSucess = true
                            }
                            .map {
                                personItemMapper.mapToPresentation(it)
                            }
                            .subscribe({
                                person.value = it
                            }, { })
            )
            add(
                    movieByPersonUseCase.createObservable(MovieByPersonUseCase.Params(personId))
                            .subscribeOn(scheduleProvider.io())
                            .observeOn(scheduleProvider.ui())
                            .doOnSuccess {

                            }
                            .doAfterTerminate { }
                            .subscribe()
                             )

        }
    }
}
