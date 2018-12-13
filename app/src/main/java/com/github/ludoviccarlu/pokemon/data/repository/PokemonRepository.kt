package com.github.ludoviccarlu.pokemon.data.repository

import com.github.ludoviccarlu.pokemon.domain.Pokemon
import io.reactivex.Single

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

interface PokemonRepository {

    fun getPokemonList() : Single<List<Pokemon>>

    //fun getPokemonById(pokemonId : Int) : Pokemon?

}