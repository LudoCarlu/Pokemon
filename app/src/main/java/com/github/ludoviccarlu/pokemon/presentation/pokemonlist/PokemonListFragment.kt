package com.github.ludoviccarlu.pokemon.presentation.pokemonlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon_list.*


class PokemonListFragment : Fragment() {

    private lateinit var viewModel : PokemonViewModel

    companion object {
        fun newInstance() = PokemonListFragment()
        private const val TAG = "PokemonListFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }

    }

    private fun setupRecyclerView(listModel: List<Pokemon>) {
        val adapter = recycler_list_pokemon.adapter as PokemonAdapter
        adapter.listModel = listModel
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(context!!, 2)
        //val linearLayoutManager = LinearLayoutManager(context!!)
        recycler_list_pokemon.layoutManager = gridLayoutManager
        recycler_list_pokemon.adapter = PokemonAdapter(context!!, mutableListOf())
    }


    private fun initObserver() {
        viewModel.liveDataListPokemon.observe(this, Observer {
            listModel -> if(listModel != null) setupRecyclerView(listModel)
        })
    }
}
