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
import kotlinx.android.synthetic.main.activity_pokemon_list.*
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.android.synthetic.main.fragment_pokemon_type_list.*


class PokemonTypeListFragment : Fragment() {

    private lateinit var viewModel : PokemonViewModel
    private lateinit var adapter : PokemonAdapter
    private lateinit var searchAdapter: PokemonAdapter
    private lateinit var typeList : List<Pokemon>


    companion object {
        fun newInstance() = PokemonTypeListFragment()
        private const val TAG = "PokemonTypeListFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_type_list, container, false)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PokemonViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gridLayoutManager = GridLayoutManager(context!!, 2)
        //val linearLayoutManager = LinearLayoutManager(context!!)
        recycler_type_list_pokemon.layoutManager = gridLayoutManager
        recycler_type_list_pokemon.adapter = PokemonAdapter(context!!, mutableListOf())

        //Setup search bar
        search_bar_type.setHint("Enter Pokemon Name")
        search_bar_type.setCardViewElevation(10)
        search_bar_type.lastSuggestions = viewModel.last_suggest


        search_bar_type.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val suggest = ArrayList<String>()
                for (search in viewModel.last_suggest) {
                    if (search.toLowerCase().contains(search_bar_type.text.toLowerCase())) {
                        suggest.add(search)
                    }
                }
                search_bar_type.lastSuggestions = suggest

            }

        })
        search_bar_type.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {
            }

            override fun onSearchStateChanged(enabled: Boolean) {
                if(!enabled)
                    recycler_type_list_pokemon.adapter = adapter
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                startSearch(text.toString())
            }
        })

        if (arguments != null) {
            val type = arguments!!.getString("type")
            if (type != null) {
                typeList = Common.findPokemonByType(type)
                adapter = PokemonAdapter(activity!!,typeList)
                recycler_type_list_pokemon.adapter = adapter
            }

            loadSuggest()
        }
    }

    private fun loadSuggest() {
        viewModel.last_suggest.clear()
        if (typeList.size > 0) {
            for (p in typeList) {
                viewModel.last_suggest.add(p.name!!)
            }
            search_bar_type.lastSuggestions = viewModel.last_suggest
        }
    }

    private fun startSearch(text: String) {
        if (typeList.size > 0) {
            val result = ArrayList<Pokemon>()
            for(pokemon in typeList) {
                if (pokemon.name!!.toLowerCase().contains(text.toLowerCase())) {
                    result.add(pokemon)
                }
                searchAdapter = PokemonAdapter(activity!!,result)
                recycler_type_list_pokemon.adapter = searchAdapter
            }
        }
    }


}
