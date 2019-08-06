package com.task.googlesearch.core.data.remote.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.task.googlesearch.BuildConfig
import com.task.googlesearch.core.data.remote.RemoteConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY }
    }

    single {
        val builder = OkHttpClient.Builder()

        builder.connectTimeout(RemoteConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(RemoteConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(RemoteConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(get<HttpLoggingInterceptor>())
        }

        builder.build()
    }

    single { GsonBuilder().serializeNulls().create() }

    single<Retrofit> {
        Retrofit.Builder()
                .baseUrl(RemoteConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
                .client(get<OkHttpClient>())
                .build()
    }

}
