package com.github.ludoviccarlu.pokemon.data.repository

import android.util.Log
import com.github.ludoviccarlu.pokemon.data.common.Common.commonPokemonList
import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemon
import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemonDetail
import com.github.ludoviccarlu.pokemon.data.remote.RestApiService
import com.github.ludoviccarlu.pokemon.domain.Evolution
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import io.reactivex.Observable
import io.reactivex.Single
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

@Singleton
class PokemonRepositoryImpl @Inject constructor(
        private val restApiService: RestApiService
) : PokemonRepository {
    override fun getPokemonList() : Single<List<Pokemon>> {

        return restApiService.getListPokemon()
                .map { response ->
                    if (response.isSuccessful && response.body() != null) {
                        response.body()?.pokemon
                    } else {
                        null
                    }
                }
    }

    override fun getPokemonById(id : Int): Pokemon? {

        commonPokemonList.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null

    }


}