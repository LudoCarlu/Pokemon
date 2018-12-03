package com.github.ludoviccarlu.pokemon.presentation.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.data.common.Common
import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemon
import com.github.ludoviccarlu.pokemon.presentation.detail.DetailPokemonFragment
import com.github.ludoviccarlu.pokemon.presentation.pokemonlist.PokemonListFragment
import com.github.ludoviccarlu.pokemon.presentation.pokemonlist.PokemonViewModel

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "HomeActivity"

        fun newInstance(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //toolbar.setTitle("POKEMON LIST")
        //setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
                .replace(R.id.list_pokemon_fragment, PokemonListFragment.newInstance())
                .commit()

        //Register BroadCast
        /*
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetailPokemonFragment, IntentFilter("EXTRA_DETAIL_ID_POKEMON"))
        */
    }

    //Handle Broadcast handle


    private val showDetailPokemonFragment = object:BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            if(intent.action.toString() == "EXTRA_DETAIL_ID_POKEMON") {
                //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                //supportActionBar!!.setDisplayShowHomeEnabled(true)

                //Replace Fragment
                val detailFragment = DetailPokemonFragment.getInstance()
                val idPokemon = intent.getIntExtra("idPokemon", -1)

                val bundle = Bundle()
                bundle.putInt("idPokemon",idPokemon)

                detailFragment.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()

                // Set Pokemon Name to the toolbar
                val pokemon = Common.commonPokemonList[idPokemon]
                //toolbar.title = pokemon.name



            }
        }
        
    }

}