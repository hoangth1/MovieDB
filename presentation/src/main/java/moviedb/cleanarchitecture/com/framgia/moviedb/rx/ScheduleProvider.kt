package moviedb.cleanarchitecture.com.framgia.moviedb.rx

import io.reactivex.Scheduler

interface ScheduleProvider {
    abstract fun ui(): Scheduler

    abstract fun computation(): Scheduler

    abstract fun io(): Scheduler
}
