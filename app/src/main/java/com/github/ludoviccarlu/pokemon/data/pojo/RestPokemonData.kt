package com.github.ludoviccarlu.pokemon.data.pojo

import com.github.ludoviccarlu.pokemon.domain.Pokemon

// New way to have a Pojo with KOTLIN

data class RestPokemonData (
        val count : Int,
        val next : Int,
        val previous : Int,
        val results : List<Pokemon>
)