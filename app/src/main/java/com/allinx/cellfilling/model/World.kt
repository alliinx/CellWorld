package com.allinx.cellfilling.model

import java.security.SecureRandom

class World {
    //список клеток
    private var listCell = mutableListOf<Cell>()
    //количество идущих подряд живых клеток
    private var kolAlive = 0
    //количество идущих подряд мертвых клеток
    private var kolDead = 0
    //индексы жизней в списке
    private var indLife = mutableListOf<Int>()

    fun getListCell(): MutableList<Cell>{
        return listCell
    }

    fun addCell(id: Int){
        val random = SecureRandom()
        val rand = random.nextBoolean()
        //если true добавляем живую клетку,
        //обновляем счетчики подряд идущих живых и мертвых клеток
        if(rand){
            val cell = Cell.Alive
            cell.id = id
            listCell.add(cell)
            kolAlive += 1
            kolDead = 0
        }
        //если false добавляем мертвую клетку,
        //обновляем счетчики подряд идущих живых и мертвых клеток
        else{
            val cell = Cell.Dead
            cell.id = id
            listCell.add(cell)
            kolAlive = 0
            kolDead += 1
        }
        updateWorld(id)
    }

    //добавляем или удаляем клетки "жизнь" в зависимости от счетчиков
    private fun updateWorld(id: Int){
        if(kolAlive == 3){
            val cell = Cell.Life
            cell.id = id
            indLife.add(listCell.size)
            listCell.add(cell)
            kolAlive = 0
        }
        else if(kolDead == 3){
            if(indLife.isNotEmpty()){
                listCell.removeAt(indLife[indLife.size - 1])
                indLife.removeAt(indLife.size - 1)
            }
            kolDead = 0
        }
    }
}