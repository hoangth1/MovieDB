package moviedb.cleanarchitecture.com.framgia.moviedb.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : ScheduleProvider {
    override fun ui() = AndroidSchedulers.mainThread()
    override fun computation() = Schedulers.computation()
    override fun io() = Schedulers.io()
}
