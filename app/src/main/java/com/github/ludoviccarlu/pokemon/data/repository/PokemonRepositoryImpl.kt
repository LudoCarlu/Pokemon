package com.github.ludoviccarlu.pokemon.data.repository

import com.github.ludoviccarlu.pokemon.data.remote.RestApiService
import com.github.ludoviccarlu.pokemon.domain.Pokemon

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

@Singleton
class PokemonRepositoryImpl @Inject constructor(
        private val restApiService: RestApiService
) : PokemonRepository {
    override fun getPokemonList(): List<Pokemon> {
        //TODO : Changer cette liste via WebService
        val list = arrayListOf<Pokemon>()
        list.add(Pokemon("Salameche", "url"))
        list.add(Pokemon("Carapuce", "url"))
        return list
    }


}