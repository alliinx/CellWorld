package com.allinx.cellfilling.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.allinx.cellfilling.model.Cell

class CellDiffCallback(
        private val oldLIst: List<Cell>,
        private val newList: List<Cell>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldLIst.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLIst[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLIst[oldItemPosition].id == newList[newItemPosition].id &&
                oldLIst[oldItemPosition].title == newList[newItemPosition].title
    }
}