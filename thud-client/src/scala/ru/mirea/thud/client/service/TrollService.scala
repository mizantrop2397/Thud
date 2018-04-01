package ru.mirea.thud.client.service

import java.util

import ru.mirea.thud.client.app.ThudGame
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.constants.FieldCellType.{DWARF, EMPTY, TROLL}
import ru.mirea.thud.client.constants.CellTargetMode.{ATTACK, MOVE}
import ru.mirea.thud.client.model.messages.{HighlightCellsMessage, DeleteFiguresMessage}
import ru.mirea.thud.client.model.{Cell, FieldUnit}
import ru.mirea.thud.common.model.Location

/*
Order of neighbors in list (numbers = indexes)
4  0  5
3  X  1
7  2  6
*/
object TrollService{

  private var cellsToHighlightAttack = new util.ArrayList[FieldUnit]
  private var cellsToHighlightMove = new util.ArrayList[FieldUnit]

  def getCellsToHighlightAttack: util.ArrayList[FieldUnit] = cellsToHighlightAttack
  def getCellsToHighlightMove: util.ArrayList[FieldUnit] = cellsToHighlightMove
  /*
    Calculate possible moves
  */
  def calculateTrollMovement(controllingUnit: FieldUnit): Unit = {
    def action : CommonUnitActions = new CommonUnitActions()
    cellsToHighlightAttack = checkForTrollsAttack(controllingUnit)
    cellsToHighlightMove = checkForTrollsMovement(controllingUnit)
    var map : Map[Location, CellTargetMode.Value] = action.addToMap(cellsToHighlightAttack, ATTACK)
    map.++(action.addToMap(cellsToHighlightMove, MOVE))
    ThudGame.fieldController ! HighlightCellsMessage (map)
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
        if (Cell.isCellTroll(neighbor) && ((i < 2 && controllingUnit.neighbors(i+2).cellType.equals(TROLL)) && (i > 2 && controllingUnit.neighbors(i-2).cellType.equals(TROLL)))){
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
        if (Cell.isCellEmpty(neighbor)){
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
      neighbor.cellType = EMPTY
      dwarfsToKill.add(neighbor)
    }
    if (!dwarfsToKill.isEmpty){
      ThudGame.fieldController ! DeleteFiguresMessage(dwarfsToKill)
    }
  }
}
