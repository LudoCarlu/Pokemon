package com.github.ludoviccarlu.pokemon.presentation.pokemonlist


import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import com.github.ludoviccarlu.pokemon.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.pokemon_list_item.view.*
import kotlinx.android.synthetic.main.pokemon_view_holder.view.*

class  PokemonAdapter(val context: Context, var listModel: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_view_holder, parent, false)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return ViewHolder(context, itemView)
    }

    override fun getItemCount() = listModel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(listModel[position])


    class ViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(model: Pokemon) {

            with(itemView) {

                pokemon_name.text = model.name
                Glide.with(context).load(model.img).into(pokemon_image)

                itemView.setOnClickListener {

                    //LocalBroadcastManager.getInstance(context)
                    //        .sendBroadcast(Intent("EXTRA_DETAIL_ID_POKEMON").putExtra("idPokemon",model.id))

                    context.startActivity(DetailActivity.newInstance(context, model.id))
                    Toast.makeText(context, "Clicked on "+ model.name, Toast.LENGTH_SHORT).show()

                }

            }
        }
    }



}