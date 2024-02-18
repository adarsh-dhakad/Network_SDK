package com.devrev.network.di.module

import com.devrev.network.BuildConfig.API_KEY
import com.devrev.network.Constants
import com.devrev.network.api.MovieService
import com.devrev.network.repository.MoviesRemoteDataSource
import com.devrev.network.repository.MoviesRemoteDataSourceImpl
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val  networkModule = module {
    single(named("TMDB_KEY")) { provideApiKey() }
    single<OkHttpClient.Builder> { OkHttpClient.Builder() }
    single { provideClient(get(), apiKey = get(named("TMDB_KEY"))) }
    single { provideRetrofitBuilder(okHttpClient = get()) }
    single { provideMoviesService(get()) }
    single<MoviesRemoteDataSource> { MoviesRemoteDataSourceImpl(get(),get()) }
}

internal fun provideApiKey(): String = API_KEY

    fun provideClient(
        clientBuilder: OkHttpClient.Builder,
        apiKey: String
    ): OkHttpClient {
        clientBuilder
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", apiKey).build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)

        return clientBuilder.build()
    }

    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

fun provideMoviesService(
    retrofit: Retrofit
): MovieService = retrofit.create(MovieService::class.java)