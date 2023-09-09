package com.openweb.pokemons.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openweb.pokemons.base.PokemonApp
import com.openweb.pokemons.bl.usecases.GetPokemonUseCase
import com.openweb.pokemons.dl.models.Pokemon
import com.openweb.pokemons.dl.services.PokemonsService
import com.openweb.pokemons.ui.catalog.PokemonListItem
import com.openweb.pokemons.utils.update
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PokemonsViewModel : ViewModel() {

    // can be replaced by Koin
    private val pokemonsService by lazy { PokemonsService(PokemonApp.context) }
    private val getPokemonsUseCase by lazy { GetPokemonUseCase(pokemonsService) }

    private val uiState = MutableStateFlow(
        UiState(
            isPokemonLoading = false,
            focusedPokemon = null,
            pokemonList = emptyList(),
            pokemonsLoadingFailure = null
        )
    )

    val focusedPokemon = uiState.map { it.focusedPokemon }
    val pokemonsList =
        uiState.map { state ->
            state.pokemonList
                .map { pokemonItem -> PokemonListItem(pokemonItem) }
        }

    val isPokemonLoading = uiState.map { it.isPokemonLoading }
    val errorMessage = uiState.map { it.pokemonsLoadingFailure?.message.orEmpty() }

    fun loadPokemons() {
        viewModelScope.launch {
            uiState.update { it.copy(isPokemonLoading = true) }
            getPokemonsUseCase.getAllPokemons(onSuccess = { pokemons ->
                uiState.update { it.copy(isPokemonLoading = false, pokemonList = pokemons) }
            }, onFailure = { ex ->
                uiState.update { it.copy(isPokemonLoading = false, pokemonsLoadingFailure = ex) }
            })
        }
    }

    fun loadPoekmonById(id: Int) {
        uiState.update {
            it.copy(focusedPokemon = it.pokemonList.firstOrNull { it.id == id })
        }
    }
}

private data class UiState(
    val isPokemonLoading: Boolean,
    val focusedPokemon: Pokemon?,
    val pokemonList: List<Pokemon>,
    val pokemonsLoadingFailure: Throwable?
)
