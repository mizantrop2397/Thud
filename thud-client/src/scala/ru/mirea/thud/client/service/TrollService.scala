package ru.mirea.thud.client.service

import java.util

import ru.mirea.thud.client.model.FieldUnit
import ru.mirea.thud.client.constants.FieldCellType.{DWARF, EMPTY, TROLL}

/*
Order of neighbors in list (numbers = indexes)
4  0  5
3  X  1
7  2  6
*/
class TrollService extends PlayerService{

  /*
    Calculate possible moves
  */
  def calculateTrollMovement(controllingUnit: FieldUnit): Unit = {
    checkForTrollsAttack(controllingUnit)
    checkForTrollsMovement(controllingUnit)
  }

 /*
    Check cells for attack possibility
      return ArrayList
 */
  private def checkForTrollsAttack(controllingUnit: FieldUnit): util.ArrayList[FieldUnit] = {
    var possibleCells = new util.ArrayList[FieldUnit]()
    (0 to 3).foreach{
      i => {
        var count = 0
        val neighbor = controllingUnit.neighbors(i)
        if (isCellTroll(neighbor) && ((i < 2 && controllingUnit.neighbors(i+2).cellType.equals(TROLL)) && (i > 2 && controllingUnit.neighbors(i-2).cellType.equals(TROLL)))){
          count = countLineLength(neighbor, i)
        }
        var index = i
        if (i < 2){
          index = index + 2
        } else {
          index = index - 2
        }
        (0 to count).foreach{
          j => {
            if (controllingUnit.neighbors(index).cellType.equals(EMPTY)){
              possibleCells.add(controllingUnit.neighbors(index))
            }
            controllingUnit.copy(controllingUnit.neighbors(index).location, controllingUnit.neighbors(index).cellType, controllingUnit.neighbors(index).neighbors)
          }
        }
      }
    }
    possibleCells
  }

  private def countLineLength (currentUnit: FieldUnit, index: Int): Int = {
    var count = 0
    if (currentUnit.neighbors(index).cellType.equals(TROLL)){
      count = countLineLength(currentUnit.neighbors(index), index)
    }
    count + 1
  }

  /*
    Check cells for movement possibility
      return ArrayList
  */
  private def checkForTrollsMovement(controllingUnit: FieldUnit): util.ArrayList[FieldUnit] = {
    var possibleCells = new util.ArrayList[FieldUnit]()
    (4 to 7).foreach{
      i => {
        val neighbor = controllingUnit.neighbors(i)
        if (isCellEmpty(neighbor)){
          possibleCells.add(neighbor)
        }
      }
    }
    possibleCells
  }


  /*
    Setting cells with dwarfs to empty and notify other player
  */
  def processTrollAttack(selectedCell: FieldUnit): Unit = {
    var dwarfsToKill = new util.ArrayList[FieldUnit]()
    for (neighbor <- selectedCell.neighbors if neighbor.cellType.equals(DWARF)) {
      //neighbor.cellType = EMPTY - хз почему не работает, я тупенькая
      dwarfsToKill.add(neighbor)
    }
    if (!dwarfsToKill.isEmpty){
      //send message
    }
  }
}
