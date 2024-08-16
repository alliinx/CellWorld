package com.allinx.cellfilling.viewmodel

import androidx.lifecycle.ViewModel
import com.allinx.cellfilling.model.World

class MainViewModel: ViewModel() {
    private val world: World = World()

    private var scrollPosition = 0
    private var scrollOffset = 0
    private var id = 0

    fun getListCell() = world.getListCell()

    fun generateCell(){
        world.addCell(id)
        id += 1
    }

    fun setScrollPosition(scrollPosition: Int) {
        this.scrollPosition = scrollPosition
    }

    fun getScrollPosition(): Int {
        return scrollPosition
    }

    fun setScrollOffset(scrollOffset: Int) {
        this.scrollOffset = scrollOffset
    }

    fun getScrollOffset(): Int {
        return scrollOffset
    }
}