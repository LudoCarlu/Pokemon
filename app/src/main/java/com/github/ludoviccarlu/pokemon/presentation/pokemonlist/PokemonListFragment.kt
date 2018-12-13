package com.github.ludoviccarlu.pokemon.presentation.pokemonlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.data.common.Common
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.fragment_pokemon_list.*


class PokemonListFragment : Fragment() {

    private lateinit var viewModel : PokemonViewModel
    private lateinit var adapter : PokemonAdapter
    private lateinit var searchAdapter: PokemonAdapter
    private lateinit var searchBar: MaterialSearchBar


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
        adapter = recycler_list_pokemon.adapter as PokemonAdapter
        adapter.listModel = listModel
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(context!!, 2)
        //val linearLayoutManager = LinearLayoutManager(context!!)
        recycler_list_pokemon.layoutManager = gridLayoutManager
        recycler_list_pokemon.adapter = PokemonAdapter(context!!, mutableListOf())

        //Setup search bar
        search_bar.setHint("Enter Pokemon Name")
        search_bar.setCardViewElevation(10)
        search_bar.lastSuggestions = viewModel.last_suggest

        search_bar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val suggest = ArrayList<String>()
                for (search in viewModel.last_suggest) {
                    if (search.toLowerCase().contains(search_bar.text.toLowerCase())) {
                        suggest.add(search)
                    }
                }
                search_bar.lastSuggestions = suggest

            }

        })
        search_bar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {
            }

            override fun onSearchStateChanged(enabled: Boolean) {
                if(!enabled)
                    recycler_list_pokemon.adapter = adapter
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                startSearch(text.toString())
            }
        })
    }

    private fun startSearch(text: String) {
        if (Common.commonPokemonList.size > 0) {
            val result = ArrayList<Pokemon>()
            for(pokemon in Common.commonPokemonList) {
                if (pokemon.name!!.toLowerCase().contains(text.toLowerCase())) {
                    result.add(pokemon)
                }
                searchAdapter = PokemonAdapter(activity!!,result)
                recycler_list_pokemon.adapter = searchAdapter
            }
        }
    }


    private fun initObserver() {
        viewModel.liveDataListPokemon.observe(this, Observer {
            listModel -> if(listModel != null) setupRecyclerView(listModel)
        })
    }
}
