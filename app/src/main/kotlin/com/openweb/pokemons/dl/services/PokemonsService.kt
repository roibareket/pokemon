package com.openweb.pokemons.dl.services

import android.content.Context
import com.openweb.pokemons.dl.models.Pokemon
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.util.concurrent.Executors

typealias PokemonCallback = Result<List<Pokemon>>

interface PokemonsServicing {
    fun getPokemons(callback: (PokemonCallback) -> Unit)
}

class PokemonsService(private val context: Context): PokemonsServicing {
    private val jsonFileName = "pokemons.json"
    private val manager = context.assets
    private val executor = Executors.newSingleThreadExecutor()

    override fun getPokemons(callback: (PokemonCallback) -> Unit) {
        executor.execute {
            try {
                val inputStream = manager.open(jsonFileName)
                val pokemons = Json.decodeFromStream<List<Pokemon>>(inputStream)
                callback(Result.success(pokemons))
            } catch (error: Exception) {
                callback(Result.failure(error))
            }
        }
    }
}