package com.github.ludoviccarlu.pokemon.data.remote

import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemon
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by ludoviccarlu on 19/11/2018.
 */


interface RestApiService {

    @GET("pokedex.json")
    fun getListPokemon() : Single<Response<RestPokemon>>

}