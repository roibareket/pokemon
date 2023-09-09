package com.openweb.pokemons.bl.usecases

import com.openweb.pokemons.dl.models.Pokemon
import com.openweb.pokemons.dl.services.PokemonCallback
import com.openweb.pokemons.dl.services.PokemonsService
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonUseCase(private val pokemonsService: PokemonsService) {

    suspend fun getAllPokemons(): List<Pokemon> = withContext(Dispatchers.IO){
        suspendCoroutine<List<Pokemon>> {emitter ->
            pokemonsService.getPokemons{ result: PokemonCallback ->
                result
                    .onSuccess { emitter.resume(it) }
                    .onFailure { emitter.resumeWithException(it) }
            }
        }
    }
}