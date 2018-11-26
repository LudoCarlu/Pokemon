package com.github.ludoviccarlu.pokemon.presentation.pokemonlist


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ludoviccarlu.pokemon.R
import com.github.ludoviccarlu.pokemon.domain.Pokemon
import com.github.ludoviccarlu.pokemon.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.pokemon_view_holder.view.*

class  PokemonAdapter(val context: Context, var listModel: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_view_holder, parent, false)
        return ViewHolder(context, itemView)
    }

    override fun getItemCount() = listModel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(listModel[position])


    class ViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(model: Pokemon) {
            //TODO Récupérer l'id pour envoyer
            with(itemView) {
                item_pokemon_name.text = model.name

                itemView.setOnClickListener { context.startActivity(DetailActivity.newInstance(context, model.name))}

            }
        }
    }

    //TODO Ici pour l'image View

}