package com.openweb.pokemons.ui.welcome

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemons.R
import com.example.pokemons.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return WelcomeFragmentBinding.inflate(inflater, container, false)
            .also { binding ->
                initView(binding)
            }.root
    }

    private fun initView(binding: WelcomeFragmentBinding) {
        binding.letsCatchThemAllBtn.setOnClickListener {
            (activity as? WelcomeFragmentListener)?.goToMainPokemonsListScreen()
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        activity?.title = getString(R.string.welcome_title)
    }
}

interface WelcomeFragmentListener{
    fun goToMainPokemonsListScreen()
}