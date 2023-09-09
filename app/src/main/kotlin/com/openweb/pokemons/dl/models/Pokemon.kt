package com.openweb.pokemons.dl.models
import kotlinx.serialization.Serializable



@Serializable
data class Pokemon(val id: Int, val name: String, val description: String)