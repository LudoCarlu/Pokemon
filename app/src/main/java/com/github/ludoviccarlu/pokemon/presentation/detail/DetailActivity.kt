package com.github.ludoviccarlu.pokemon.presentation.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.data.common.Common
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    //context.startActivity(DetailActivity.newInstance(context, id))
    @Inject
    lateinit var viewModel : DetailViewModel

    companion object {
        private const val EXTRA_POKEMON_ID = "idPokemon"

        fun newInstance(context: Context, idPokemon : Int?): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_POKEMON_ID, idPokemon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        initFragment()

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showEvolutionDetail, IntentFilter(Common.KEY_NUM_EVOLUTION))
    }

    fun initFragment() {

        //Replace Fragment
        val detailFragment = DetailPokemonFragment.getInstance()
        val idPokemon = intent.getIntExtra(EXTRA_POKEMON_ID, -1)

        System.out.println("ID du Pokemon " + idPokemon)

        val bundle = Bundle()
        bundle.putInt(EXTRA_POKEMON_ID, idPokemon)

        detailFragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.detail_pokemon_container, detailFragment)
        fragmentTransaction.addToBackStack("detail")
        fragmentTransaction.commit()

    }

    private val showEvolutionDetail = object: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            if(intent.action!!.toString() == Common.KEY_NUM_EVOLUTION) {

                //Replace Fragment
                val detailFragment = DetailPokemonFragment.getInstance()
                val bundle = Bundle()
                val num = intent.getStringExtra("num")
                bundle.putString("num",num)
                detailFragment.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.remove(detailFragment) // Remove current
                fragmentTransaction.replace(R.id.detail_pokemon_container, detailFragment)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()


            }
        }

    }








}