package com.github.ludoviccarlu.pokemon.presentation.pokemonlist

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.ludoviccarlu.pokemon.data.common.Common
import com.github.ludoviccarlu.pokemon.data.repository.PokemonRepository
import com.github.ludoviccarlu.pokemon.di.PokemonApplication
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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

        val disposable = pokemonRepository.getPokemonList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list ->
                    liveDataListPokemon.value = list //TODO
                    Common.commonPokemonList = list //Utiliser pour le changement des fragments

                }, {t : Throwable? ->
                    //TODO Show Error on Screen
                    t!!.printStackTrace()
                })

        compositeDisposable.add(disposable)

    }

    private fun initializeDagger() = PokemonApplication.appComponent.inject(this)

}