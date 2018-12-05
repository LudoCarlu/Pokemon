package com.github.ludoviccarlu.pokemon.di

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

import com.github.ludoviccarlu.pokemon.data.remote.RestApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

@Module
class RemoteModule {

    @Provides @Singleton fun provideGson(): Gson =
            GsonBuilder()
                    .setLenient()
                    .create()

    @Provides @Singleton fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .hostnameVerifier { hostname, session -> true  }
                    .build()

    @Provides @Singleton fun provideRestApiService(gson: Gson, okHttpClient: OkHttpClient): RestApiService =
            Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(RestApiService::class.java)
}