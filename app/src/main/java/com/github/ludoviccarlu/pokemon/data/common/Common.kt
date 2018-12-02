package com.github.ludoviccarlu.pokemon.data.common

import com.github.ludoviccarlu.pokemon.domain.Pokemon


object Common {

    var commonPokemonList : List<Pokemon> = ArrayList<Pokemon>()


    fun getPokemonById (id : Int) : Pokemon? {

        return commonPokemonList[id]
    }

}