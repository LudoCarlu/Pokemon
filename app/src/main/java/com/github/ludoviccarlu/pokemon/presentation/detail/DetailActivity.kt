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
import android.view.KeyEvent.KEYCODE_BACK
import android.content.DialogInterface
import android.view.KeyEvent
import android.view.View
import com.github.ludoviccarlu.pokemon.presentation.pokemonlist.PokemonTypeListFragment


class DetailActivity : AppCompatActivity() {

    //context.startActivity(DetailActivity.newInstance(context, id))
    @Inject
    lateinit var viewModel : DetailViewModel

    companion object {
        private const val EXTRA_POKEMON_ID = "idPokemon"

        fun newInstance(context: Context ,idPokemon : Int?): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_POKEMON_ID, idPokemon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        initFragment()

        //Register BroadCast
        /*
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetailPokemonFragment, IntentFilter("EXTRA_DETAIL_ID_POKEMON"))
        */
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showEvolutionDetail, IntentFilter(Common.KEY_NUM_EVOLUTION))

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showTypeList, IntentFilter("type"))

    }

    fun initFragment() {

        //Replace Fragment
        val detailFragment = DetailPokemonFragment.getInstance()
        val idPokemon = intent.getIntExtra(EXTRA_POKEMON_ID, -1)

        val bundle = Bundle()
        bundle.putInt(EXTRA_POKEMON_ID, idPokemon)
        detailFragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.detail_pokemon_container, detailFragment)
        fragmentTransaction.commit()


    }

    private val showEvolutionDetail = object: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            if(intent.action!!.toString() == Common.KEY_NUM_EVOLUTION) {

                System.out.println("SHOW DETAIL EVOL")

                //Delete current fragment to replace it
                val oldFragment = DetailPokemonFragment.getInstance()
                supportFragmentManager.beginTransaction().remove(oldFragment).commitAllowingStateLoss()

                val num = intent.getStringExtra("num")

                val newFragment = DetailPokemonFragment.newInstance()

                val bundle = Bundle()

                bundle.putString("num",num)

                newFragment.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.detail_pokemon_container, newFragment)
                fragmentTransaction.commitAllowingStateLoss()


            }
        }

    }

    private val showTypeList = object: BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            if(intent.action!!.toString() == "type") {

                //Delete current fragment to replace it
                val oldFragment = DetailPokemonFragment.getInstance()
                supportFragmentManager.beginTransaction().remove(oldFragment).commitAllowingStateLoss()

                val type = intent.getStringExtra("type")

                val newFragment = PokemonTypeListFragment.newInstance()

                val bundle = Bundle()

                bundle.putString("type",type)

                newFragment.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.detail_pokemon_container, newFragment)
                fragmentTransaction.commitAllowingStateLoss()


            }
        }

    }


}