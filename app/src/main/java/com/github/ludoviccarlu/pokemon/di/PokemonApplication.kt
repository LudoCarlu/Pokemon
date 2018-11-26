package com.github.ludoviccarlu.pokemon.di

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

import android.app.Application

class PokemonApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .remoteModule(RemoteModule())
                .build()
    }
}
