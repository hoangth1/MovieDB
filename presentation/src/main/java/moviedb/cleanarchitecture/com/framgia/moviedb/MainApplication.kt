package moviedb.cleanarchitecture.com.framgia.moviedb

import android.app.Application
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.netwokModule
import moviedb.cleanarchitecture.com.framgia.moviedb.di.appModule
import moviedb.cleanarchitecture.com.framgia.moviedb.di.viewModelModule
import org.koin.android.ext.android.startKoin


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                appModule, viewModelModule, netwokModule
        ))
    }

}
