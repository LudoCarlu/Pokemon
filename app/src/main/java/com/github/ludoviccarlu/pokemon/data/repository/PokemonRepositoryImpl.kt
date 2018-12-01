package com.github.ludoviccarlu.pokemon.data.repository

import android.util.Log
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
                        System.out.println("JE SUIS LA 2")
                        response.body()?.pokemon
                    } else {
                        null
                    }
                }
        /*
        val list = arrayListOf<Pokemon>()
        list.add(Pokemon(
        ))
        list.add(Pokemon("Carapuce", "url"))
        return list*/
    }

    override fun getPokemonNameById(id : Int): Single<RestPokemonDetail> {

        return restApiService.getPokemonNameById(id)
                .map { response ->
                    if(response.isSuccessful && response.body() != null){

                        //RestPokemonDetail(JSONObject(response.body().toString()))

                        System.out.println(response.body()!!.sprites)
                        System.out.println(response.body()!!.types)


                        RestPokemonDetail(
                                response.body()!!.id,
                                response.body()!!.name,
                                response.body()!!.height,
                                response.body()!!.weight,
                                response.body()!!.sprites,
                                response.body()!!.types
                        )
                        //TODO Convert sprites et types into String
                    } else {
                        null
                    }
                }

    }

    fun getSprites (jsonObject: JSONObject) : List<String> {
        val sprites  = arrayListOf<String>()

        sprites.add(jsonObject.getString("back_default"))
        sprites.add(jsonObject.getString("front_default"))

        return sprites
    }

    fun getTypes (jsonArray: JSONArray) : List<String> {
        val types  = arrayListOf<String>()

        var i = 0

        while (i < jsonArray.length()) {
            val typeObject = JSONObject(jsonArray[i].toString())
            val type = typeObject.getJSONObject("type")
            types.add(type.getString("name"))
            i++
        }

        return types
    }


}