package com.github.ludoviccarlu.pokemon.data.pojo

import org.json.JSONObject

data class RestPokemonDetail (

        val id : Int,
        val name : String,
        val height : Int,
        val weight : Int,
        val sprites : Any,
        val types : Any
        //val sprites : List<String>,
        //val types : List<String>

        //val results : JSONObject

)

