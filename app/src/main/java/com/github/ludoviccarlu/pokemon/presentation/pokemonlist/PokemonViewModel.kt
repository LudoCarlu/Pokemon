package com.github.ludoviccarlu.pokemon.presentation.pokemonlist

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.ludoviccarlu.pokemon.data.repository.PokemonRepository
import com.github.ludoviccarlu.pokemon.di.PokemonApplication
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by ludoviccarlu on 19/11/2018.
 */


class PokemonViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var pokemonRepository: PokemonRepository

    private val compositeDisposable = CompositeDisposable()

    var liveDataListPokemon: MutableLiveData<List<Pokemon>> = MutableLiveData()


    init {
        initializeDagger()

        liveDataListPokemon.value = pokemonRepository.getPokemonList()

    }

    private fun initializeDagger() = PokemonApplication.appComponent.inject(this)

}