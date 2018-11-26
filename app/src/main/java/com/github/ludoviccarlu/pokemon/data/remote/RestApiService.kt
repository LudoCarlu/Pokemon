package com.github.ludoviccarlu.pokemon.data.remote

import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemonData
import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemonDetail
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
 * Created by ludoviccarlu on 19/11/2018.
 */


interface RestApiService {
    @GET("/api/v2/pokemon")
    fun getListPokemon() : Single<Response<RestPokemonData>>

    @GET("/api/v2/pokemon/{pokemon_id}")
    fun getPokemonNameById(@Path(value = "pokemon_id", encoded = true) pokemonId : Int): Single<Response<RestPokemonDetail>>

}