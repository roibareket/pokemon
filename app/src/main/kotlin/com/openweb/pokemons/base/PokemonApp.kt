package com.openweb.pokemons.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class PokemonApp: Application() {

    companion object{
        // can be replaced by koin
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()

        context = this
    }
}