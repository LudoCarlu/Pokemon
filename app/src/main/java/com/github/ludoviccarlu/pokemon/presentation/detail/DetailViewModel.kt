package com.github.ludoviccarlu.pokemon.presentation.detail

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemonDetail
import com.github.ludoviccarlu.pokemon.data.repository.PokemonRepository
import com.github.ludoviccarlu.pokemon.di.PokemonApplication
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import com.github.ludoviccarlu.pokemon.presentation.pokemonlist.PokemonAdapter
import com.github.ludoviccarlu.pokemon.presentation.pokemonlist.PokemonViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import javax.inject.Inject


class DetailViewModel : ViewModel() {

/*
    @Inject
    lateinit var pokemonRepository: PokemonRepository

    var pokemon : Pokemon? = null


    init {
        initializeDagger()
    }

    fun onStart(pokemonId : Int) {

        pokemon = pokemonRepository.getPokemonById(pokemonId)

    }

    private fun initializeDagger() = PokemonApplication.appComponent.inject(this)
*/
}