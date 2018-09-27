package moviedb.cleanarchitecture.com.framgia.data.source.remote.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val netwokModule = module {
    single { provideHttpClientBuilde() }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
}

fun provideHttpClientBuilde(): OkHttpClient.Builder {
    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
        var request = chain.request()
        val url = request
                .url()
                .newBuilder().addQueryParameter(Api.PARAM_API_KEY, Api.KEY)
                .build()
        request = request.newBuilder().url(url).build()
        chain.proceed(request)
    }
    return httpClient
}

fun provideRetrofit(httpClient: OkHttpClient.Builder): Retrofit {
    return Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build()).build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
