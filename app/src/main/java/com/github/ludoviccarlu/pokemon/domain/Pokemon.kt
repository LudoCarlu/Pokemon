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
    val candy_count: Int? = 0,
    val egg: String? = null,
    val spawn_chance : Double? = 0.0,
    val avg_spawn : Double? = 0.0,
    val spawn_time : String? = null,
    val multipliers : List<Double>? = null,
    val weaknesses : List<String>? = null,
    val next_evolution : List<Evolution>? = null,
    val prev_evolution : List<Evolution>? = null


)