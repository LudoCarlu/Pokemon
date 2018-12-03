package com.github.ludoviccarlu.pokemon.presentation.detail

import android.media.Image
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.data.common.Common
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import kotlinx.android.synthetic.main.fragment_detail_pokemon.*


class DetailPokemonFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    internal lateinit var pokemon_img : ImageView
    internal lateinit var pokemon_name : TextView
    internal lateinit var pokemon_height : TextView
    internal lateinit var pokemon_weight : TextView

    internal lateinit var recycler_type : RecyclerView
    internal lateinit var recycler_weaknesses : RecyclerView
    internal lateinit var recycler_prev_evolution : RecyclerView
    internal lateinit var recycler_next_evolution : RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val itemView = inflater.inflate(R.layout.fragment_detail_pokemon, container, false)

        val pokemon: Pokemon?

        if (arguments!!.getString("num") == null) {
            pokemon = Common.getPokemonById(arguments!!.getInt("idPokemon"))
            System.out.println("NUM NULL " + pokemon!!.id)
        }
        else {
            pokemon = Common.findPokemonByNum(arguments!!.getString("num"))
            System.out.println("NUM NOT NULL" + pokemon!!.id)
        }


        pokemon_img = itemView.findViewById(R.id.pokemon_image) as ImageView
        pokemon_name = itemView.findViewById(R.id.name) as TextView
        pokemon_height = itemView.findViewById(R.id.height) as TextView
        pokemon_weight = itemView.findViewById(R.id.weight) as TextView

        recycler_next_evolution = itemView.findViewById(R.id.recycler_next_evolution) as RecyclerView
        recycler_next_evolution.setHasFixedSize(true)
        recycler_next_evolution.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_prev_evolution = itemView.findViewById(R.id.recycler_prev_evolution) as RecyclerView
        recycler_prev_evolution.setHasFixedSize(true)
        recycler_prev_evolution.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_type = itemView.findViewById(R.id.recycler_type) as RecyclerView
        recycler_type.setHasFixedSize(true)
        recycler_type.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        recycler_weaknesses = itemView.findViewById(R.id.recycler_weaknesses) as RecyclerView
        recycler_weaknesses.setHasFixedSize(true)
        recycler_weaknesses.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        setDetailPokemon(pokemon)

        return itemView
    }

    private fun setDetailPokemon(pokemon: Pokemon?) {
        //Load image

        Glide.with(activity!!).load(pokemon!!.img).into(pokemon_img)

        pokemon_name.text = pokemon.name
        pokemon_height.text = "Height: "+pokemon.height
        pokemon_weight.text = "Weight: "+pokemon.weight

        val typeAdapter = PokemonTypeAdapter(activity!!, pokemon.type!!)
        recycler_type.adapter = typeAdapter

        val weaknessesAdapter = PokemonTypeAdapter(activity!!, pokemon.weaknesses!!)
        recycler_weaknesses.adapter = weaknessesAdapter


        if(pokemon.prev_evolution != null) {

            val prevEvolutionAdapter = PokemonEvolutionAdapter(activity!!, pokemon.prev_evolution)
            recycler_prev_evolution.adapter = prevEvolutionAdapter
        }

        if(pokemon.next_evolution != null) {

            val nextEvolutionAdapter = PokemonEvolutionAdapter(activity!!, pokemon.next_evolution)
            recycler_next_evolution.adapter = nextEvolutionAdapter
        }


    }

    companion object {

        internal var instance: DetailPokemonFragment?=null


        fun getInstance() : DetailPokemonFragment {
            if (instance == null) {
                instance = newInstance()
            }
            return instance!!
        }

        @JvmStatic
        fun newInstance() = DetailPokemonFragment()
    }

}
