package moviedb.cleanarchitecture.com.framgia.moviedb.screen.main

import android.arch.lifecycle.MutableLiveData
import moviedb.cleanarchitecture.com.framgia.moviedb.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    val title = MutableLiveData<String>()
}
