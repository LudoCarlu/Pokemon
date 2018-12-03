package com.github.ludoviccarlu.pokemon.data.repository

import com.github.ludoviccarlu.pokemon.domain.Pokemon

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

interface PokemonRepository {

    fun getPokemonList() : List<Pokemon>
}