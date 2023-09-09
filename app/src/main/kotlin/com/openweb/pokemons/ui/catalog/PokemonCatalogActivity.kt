package com.openweb.pokemons.ui.catalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.pokemons.R
import com.example.pokemons.databinding.ActivityPokemonCatalogBinding

class PokemonCatalogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            ActivityPokemonCatalogBinding.inflate(layoutInflater).root
        )
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, PokemonCatalogActivity::class.java)
    }
}