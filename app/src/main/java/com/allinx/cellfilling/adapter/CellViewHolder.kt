package com.allinx.cellfilling.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.allinx.cellfilling.databinding.ItemCellBinding
import com.allinx.cellfilling.model.Cell

class CellViewHolder(
    private val binding: ItemCellBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Cell) {
        Log.d("MeLog","bind")
        val context = this.itemView.context
        with(binding) {
            cellTitle.text = getString(context,item.title)
            cellDescription.text = getString(context,item.description)
            icCell.load(item.ic)
            bgCell.load(item.bg)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ) = CellViewHolder(
            ItemCellBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}