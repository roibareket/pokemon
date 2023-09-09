package com.openweb.pokemons.ui.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemons.R
import com.openweb.pokemons.ui.catalog.PokemonCatalogActivity

class WelcomeActivity : AppCompatActivity(), WelcomeFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeFragment.newInstance())
                    .commit()
        }
    }

    override fun goToMainPokemonsListScreen() {
        startActivity(PokemonCatalogActivity.newIntent(this))
        finish()
    }
}