package com.github.ludoviccarlu.pokemon.presentation.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.ludoviccarlu.pokemon.R
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : DetailViewModel

    companion object {
        private const val EXTRA_POKEMON_ID = "idPokemon"

        // TODO dans POkemonADpater Passer l'id du pokemon en deuxième
        fun newInstance(context: Context, idPokemon : Int): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_POKEMON_ID, idPokemon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        initViewModel()
        initObserver()


        viewModel.onStart(intent.getIntExtra(EXTRA_POKEMON_ID, -1))
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }
    }

    private fun initObserver() {
        //TODO liveDATA champion a chnager
        viewModel.liveDataChampion.observe(this, Observer {
            restPokemonData -> restPokemonData?.let {
            pokemon_name.text = restPokemonData.name
            pokemon_title.text = restPokemonData.title
            pokemon_description.text = restPokemonData.description
        }
        })
    }
}