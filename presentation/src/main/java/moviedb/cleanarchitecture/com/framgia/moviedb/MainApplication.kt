package moviedb.cleanarchitecture.com.framgia.moviedb

import android.app.Application
import moviedb.cleanarchitecture.com.framgia.data.di.netwokModule
import moviedb.cleanarchitecture.com.framgia.data.di.repositoryModule
import moviedb.cleanarchitecture.com.framgia.moviedb.di.appModule
import moviedb.cleanarchitecture.com.framgia.moviedb.di.useCaseModule
import moviedb.cleanarchitecture.com.framgia.moviedb.di.viewModelModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                appModule, viewModelModule, netwokModule, repositoryModule, useCaseModule
        ))
    }
}
