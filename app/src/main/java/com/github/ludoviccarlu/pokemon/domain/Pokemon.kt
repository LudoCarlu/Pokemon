package com.github.ludoviccarlu.pokemon.domain

import android.text.style.LineHeightSpan

/**
 * Created by ludoviccarlu on 19/11/2018.
 */

data class Pokemon(

    val id: Int? = 0,
    val num: String? = null,
    val name: String? = null,
    val img: String? = null,
    val type: List<String>? = null,
    val height: String? = null,
    val weight: String? = null,
    val candy: String? = null,
    val candyCount: Int? = 0,
    val egg: String? = null,
    val spawnChance : Double? = 0.0,
    val avgSpawn : Double? = 0.0,
    val spawnTime : String? = null,
    val multipliers : List<Double>? = null,
    val weaknesses : List<String>? = null,
    val nextEvolution : List<Evolution>? = null,
    val prevEvolution : List<Evolution>? = null


)