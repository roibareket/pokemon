package com.openweb.pokemons.bl.usecases

import com.openweb.pokemons.dl.models.Pokemon
import com.openweb.pokemons.dl.services.PokemonCallback
import com.openweb.pokemons.dl.services.PokemonsService

class GetPokemonUseCase(private val pokemonsService: PokemonsService) {

    fun getAllPokemons(onSuccess: (List<Pokemon>)-> Unit, onFailure: (Throwable) -> Unit){
        pokemonsService.getPokemons{ result: PokemonCallback ->
            result
                .onSuccess { onSuccess(it) }
                .onFailure { onFailure(it) }
        }
    }
}