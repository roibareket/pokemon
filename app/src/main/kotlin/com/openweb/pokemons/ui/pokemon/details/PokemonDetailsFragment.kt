package com.openweb.pokemons.ui.pokemon.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pokemons.databinding.FragmentPokemonDetailsBinding
import com.openweb.pokemons.ui.PokemonsViewModel
import com.openweb.pokemons.ui.catalog.getPokemonImage
import com.openweb.pokemons.utils.observe

class PokemonDetailsFragment : Fragment() {

    val args: PokemonDetailsFragmentArgs by navArgs()
    val pokemonsViewModel by activityViewModels<PokemonsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentPokemonDetailsBinding.inflate(inflater, container, false).apply {
            initView(this)
            if(savedInstanceState == null) {
                pokemonsViewModel.loadPoekmonById(args.pokemonId)
            }
        }.root

    private fun initView(binding: FragmentPokemonDetailsBinding) {
        pokemonsViewModel.focusedPokemon.observe(viewLifecycleOwner){pokemon ->
            binding.pokemonAvatarAndName.apply {
                pokemonImage.setImageDrawable(pokemon?.getPokemonImage(binding.root.context))
                pokemonName.text = pokemon?.name
            }
            binding.pokemonDetails.text = pokemon?.description
        }
    }
}