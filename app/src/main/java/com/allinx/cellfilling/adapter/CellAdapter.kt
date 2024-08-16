package com.allinx.cellfilling.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.allinx.cellfilling.databinding.ItemCellBinding
import com.allinx.cellfilling.model.Cell

class CellAdapter (
    private var listCell: List<Cell>
) : RecyclerView.Adapter<CellAdapter.ItemsViewHolder>() {

    class ItemsViewHolder(private val binding: ItemCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Cell) {
            val context = this.itemView.context
            with(binding) {
                cellTitle.text = getString(context,item.title)
                cellDescription.text = getString(context,item.description)
                icCell.load(item.ic)
                bgCell.load(item.bg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val binding = ItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCell.size
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(listCell[position])
    }

    fun updateCocktails(newListCell: List<Cell>) {
        val diffUtilDrinks = CellDiffCallback(listCell, newListCell)
        val diffResults = DiffUtil.calculateDiff(diffUtilDrinks)
        this.listCell = newListCell
        diffResults.dispatchUpdatesTo(this)
    }
}