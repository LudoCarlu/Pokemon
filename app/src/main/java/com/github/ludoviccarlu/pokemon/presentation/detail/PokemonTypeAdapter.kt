package com.github.ludoviccarlu.pokemon.presentation.detail

import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.data.common.Common
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import kotlinx.android.synthetic.main.pokemon_list_item.view.*
import com.robertlevonyan.views.chip.Chip
import kotlinx.android.synthetic.main.chip_item.view.*

class PokemonTypeAdapter(var context:Context, var typeList:List<String>) : RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.chip_item, parent, false)
        return ViewHolder(context, itemView)
    }

    override fun getItemCount() = typeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(typeList[position])


    class ViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        //internal var chip: Chip

        fun bindItem(model: String) {

            with(itemView) {

                chip.chipText = model
                chip.changeBackgroundColor(Common.getColorByType(model))

                chip.setOnChipClickListener {

                    //Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()


                    LocalBroadcastManager.getInstance(context)
                            .sendBroadcast(Intent("type").putExtra("type",model))

                }

            }
        }
    }



}