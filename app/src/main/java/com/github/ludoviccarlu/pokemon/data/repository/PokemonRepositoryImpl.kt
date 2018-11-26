package com.github.ludoviccarlu.pokemon.data.repository

import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemonDetail
import com.github.ludoviccarlu.pokemon.data.remote.RestApiService
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

@Singleton
class PokemonRepositoryImpl @Inject constructor(
        private val restApiService: RestApiService
) : PokemonRepository {
    override fun getPokemonList(): Single<List<Pokemon>> {

        return restApiService.getListPokemon()
                .map { response ->
                    if(response.isSuccessful && response.body() != null){
                         response.body()?.results
                    } else {
                        null
                    }
                }
        /*
        val list = arrayListOf<Pokemon>()
        list.add(Pokemon("Salameche", "url"))
        list.add(Pokemon("Carapuce", "url"))
        return list*/
    }

    override fun getPokemonNameById(id : Int): Single<RestPokemonDetail> {

        return restApiService.getPokemonNameById(id)
                .map { response ->
                    if(response.isSuccessful && response.body() != null){
                        response.body()?.results //TODO Remplacer par le truc renvoyer
                    } else {
                        null
                    }
                }

    }


}