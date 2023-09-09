package com.openweb.pokemons.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.databinding.FragmentPokemonCatalogListBinding
import com.example.pokemons.databinding.ListItemPokemonBinding
import com.openweb.pokemons.dl.models.Pokemon
import com.openweb.pokemons.ui.PokemonsViewModel
import com.openweb.pokemons.utils.observe

class PokemonCatalogListFragment : Fragment() {

    val pokemonsViewModel by activityViewModels<PokemonsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPokemonCatalogListBinding.inflate(inflater, container, false).also { binding ->
            initView(binding)
            if(savedInstanceState == null) {
                pokemonsViewModel.loadPokemons()
            }
        }.root

    private fun initView(binding: FragmentPokemonCatalogListBinding) {
        binding.pokemonsList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = pokemonsAdapter(
                onPokemonClick = {pokemon ->
                    findNavController().navigate(PokemonCatalogListFragmentDirections.viewPokemonDetails(pokemon.id))
                }
            ).also { adapter ->
                pokemonsViewModel.pokemonsList.observe(viewLifecycleOwner) {
                    adapter.items = it
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        fun newInstance() = PokemonCatalogListFragment()
    }
}