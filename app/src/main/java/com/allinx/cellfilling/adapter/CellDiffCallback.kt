package com.allinx.cellfilling.adapter

import androidx.recyclerview.widget.DiffUtil
import com.allinx.cellfilling.model.Cell

class CellDiffCallback: DiffUtil.ItemCallback<Cell>() {

    override fun areItemsTheSame(
        oldItem: Cell,
        newItem: Cell
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: Cell,
        newItem: Cell
    ): Boolean = oldItem == newItem
}