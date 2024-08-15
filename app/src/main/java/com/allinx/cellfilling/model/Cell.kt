package com.allinx.cellfilling.model

import com.allinx.cellfilling.R

sealed class Cell {
    data object Alive : Cell()
    data object Dead : Cell()
    data object Life : Cell()

    val title: Int
        get() = when (this) {
            is Dead -> R.string.dead_title
            is Alive -> R.string.alive_title
            is Life -> R.string.life_title
        }

    val description: Int
        get() = when (this) {
            is Dead -> R.string.dead_description
            is Alive -> R.string.alive_description
            is Life -> R.string.life_description
        }

    val ic: Int
        get() = when (this) {
            is Dead -> R.drawable.ic_dead_cell
            is Alive -> R.drawable.ic_alive
            is Life -> R.drawable.ic_life
        }

    val bg: Int
        get() = when (this) {
            is Dead -> R.drawable.bg_dead_cell
            is Alive -> R.drawable.bg_alive_cell
            is Life -> R.drawable.bg_life_cell
        }
}

