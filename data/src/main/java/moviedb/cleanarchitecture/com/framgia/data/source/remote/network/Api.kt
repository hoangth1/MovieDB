package moviedb.cleanarchitecture.com.framgia.data.source.remote.network

import moviedb.cleanarchitecture.com.framgia.data.BuildConfig

interface Api {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val KEY = "d107661962965284a68ab916fb8bd204"
        const val PARAM_API_KEY = "api_key"
    }
}
