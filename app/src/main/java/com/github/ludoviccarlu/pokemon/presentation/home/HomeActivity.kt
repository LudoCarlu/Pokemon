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


        supportFragmentManager.beginTransaction()
                .replace(R.id.list_pokemon_fragment, PokemonListFragment.newInstance())
                .commit()

    }

}