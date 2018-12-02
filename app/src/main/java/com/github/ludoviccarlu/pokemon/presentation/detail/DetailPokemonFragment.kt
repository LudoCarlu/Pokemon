package com.github.ludoviccarlu.pokemon.presentation.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.data.common.Common
import kotlinx.android.synthetic.main.fragment_detail_pokemon.*


class DetailPokemonFragment : Fragment() {

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pokemon, container, false)
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
