package com.github.ludoviccarlu.pokemon.data.pojo

data class RestPokemonDetail (
        val id : Int,
        val name : String,
        val height : Int,
        val weight : Int,
        val imageUrls : List<String>,
        val types : List<String>
)