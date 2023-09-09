package com.openweb.pokemons.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> MutableStateFlow<T>.update(action: (T) -> T) {
    value = action(value)
}

fun <T> Flow<T>.observe(
    viewLifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    onEach: suspend (T) -> Unit
) =
    onEach(onEach)
        .flowWithLifecycle(viewLifecycleOwner.lifecycle, minActiveState)
        .launchIn(viewLifecycleOwner.lifecycleScope)