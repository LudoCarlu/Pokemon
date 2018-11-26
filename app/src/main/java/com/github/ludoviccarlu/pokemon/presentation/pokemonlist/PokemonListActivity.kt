package com.github.ludoviccarlu.pokemon.presentation.pokemonlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon_list.*

class PokemonListActivity : AppCompatActivity() {

    private lateinit var viewModel : PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list)
        initViewModel()
        initObserver()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }

    }

    private fun initObserver() {
        viewModel.liveDataListPokemon.observe(this, Observer {
            list -> if(list != null) setupRecyclerView(list)
        })
    }

    private fun setupRecyclerView(list : List<Pokemon>) {
        // Grid layout manager pour Card
        val gridLayoutManager = GridLayoutManager(this, 2)
        val linearLayoutManager = LinearLayoutManager(this)
        recycler_list_pokemon.layoutManager = gridLayoutManager
        recycler_list_pokemon.adapter = PokemonAdapter(this, list)

    }


}
