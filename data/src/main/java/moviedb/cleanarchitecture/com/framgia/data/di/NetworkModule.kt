package moviedb.cleanarchitecture.com.framgia.data.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.ApiConstants
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.GenreApi
import moviedb.cleanarchitecture.com.framgia.data.source.remote.network.MovieApi
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val netwokModule = module {
    single { provideHttpClientBuilde() }
    single { provideRetrofit(get()) }
    single { provideGenreApi(get()) }
    single { provideMovieApi(get()) }
}

fun provideHttpClientBuilde(): OkHttpClient.Builder {
    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
        var request = chain.request()
        val url = request
                .url()
                .newBuilder().addQueryParameter(ApiConstants.PARAM_API_KEY, ApiConstants.KEY)
                .build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }
    return httpClient
}

fun provideRetrofit(httpClient: OkHttpClient.Builder): Retrofit {
    return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build()).build()
}

fun provideGenreApi(retrofit: Retrofit): GenreApi = retrofit.create(GenreApi::class.java)
fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)
