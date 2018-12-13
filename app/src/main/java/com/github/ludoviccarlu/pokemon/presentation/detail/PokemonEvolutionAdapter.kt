package com.github.ludoviccarlu.pokemon.presentation.detail

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.data.common.Common
import com.github.ludoviccarlu.pokemon.domain.Evolution
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import kotlinx.android.synthetic.main.chip_item.view.*
import com.robertlevonyan.views.chip.Chip


class PokemonEvolutionAdapter(var context: Context, var evolutionList:List<Evolution>) : RecyclerView.Adapter<PokemonEvolutionAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.chip_item, parent, false)
        return ViewHolder(context, itemView)
    }

    override fun getItemCount() = evolutionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(evolutionList[position])


    class ViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        //internal var chip: Chip

        fun bindItem(model: Evolution) {

            with(itemView) {

                chip.chipText = model.name
                chip.changeBackgroundColor(Common.getColorByType(Common.findPokemonByNum(model.num)!!.type!![0]))

                chip.setOnChipClickListener {

                    Toast.makeText(context, "Clicked on : " + model.name, Toast.LENGTH_SHORT).show()


                    LocalBroadcastManager.getInstance(context)
                            .sendBroadcast(Intent(Common.KEY_NUM_EVOLUTION).putExtra("num",model.num))

                }

            }
        }
    }



}