package com.openweb.pokemons.ui.catalog

import android.R.attr
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.example.pokemons.databinding.ListItemPokemonBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.openweb.pokemons.dl.models.Pokemon


fun pokemonsAdapter(onPokemonClick: (Pokemon) -> Unit) = ListDelegationAdapter<List<AdapterDisplayableItem>>(
    adapterDelegateViewBinding<PokemonListItem, AdapterDisplayableItem, ListItemPokemonBinding>(
        viewBinding = { inflater, container ->
            ListItemPokemonBinding.inflate(inflater, container, false)
        }
    ) {
        bind {
            binding.apply {
                pokemonImage.setImageDrawable(item.pokemon.getPokemonImage(root.context))
                pokemonName.text = item.pokemon.name

                binding.root.setOnClickListener {
                    onPokemonClick(item.pokemon)
                }
            }
        }
    }
)

fun Pokemon.getPokemonImage(context: Context): Drawable? {
    val resources = context.resources

    val resourceId = resources.getIdentifier(
        String.format("pokemon_%03d", id), "drawable",
        context.packageName
    )

    return ResourcesCompat.getDrawable(resources, resourceId, null)
}

interface AdapterDisplayableItem

data class PokemonListItem(
    val pokemon: Pokemon
) : AdapterDisplayableItem

