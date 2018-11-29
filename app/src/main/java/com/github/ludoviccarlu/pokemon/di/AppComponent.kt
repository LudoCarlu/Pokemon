package com.github.ludoviccarlu.pokemon.di

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

import com.github.ludoviccarlu.pokemon.presentation.detail.DetailViewModel
import com.github.ludoviccarlu.pokemon.presentation.pokemonlist.PokemonViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RemoteModule::class])
@Singleton
interface AppComponent {

    fun inject(viewModel: PokemonViewModel)
    fun inject(viewModel: DetailViewModel)


}