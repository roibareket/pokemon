package com.openweb.pokemons.ui.welcome

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemons.R
import com.openweb.pokemons.services.PokemonsService

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        activity?.title = getString(R.string.welcome_title)
    }
}