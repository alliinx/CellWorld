package com.allinx.cellfilling.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.allinx.cellfilling.model.Cell

class CellAdapter : ListAdapter<Cell, CellViewHolder>(CellDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CellViewHolder.create(parent)

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Cell>?) {
        super.submitList(
            if (list == null) null
            else ArrayList(list)
        )
    }
}