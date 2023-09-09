package com.openweb.pokemons.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemons.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WelcomeFragment.newInstance())
                    .commitNow()
        }
    }
}