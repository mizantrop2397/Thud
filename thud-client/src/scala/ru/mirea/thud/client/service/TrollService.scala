package ru.mirea.thud.client.service

import java.util

import ru.mirea.thud.client.app.ThudGame.fieldService
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.constants.CellTargetMode.{ATTACK, MOVE}
import ru.mirea.thud.client.model.Cell._
import ru.mirea.thud.client.model.messages.HighlightCellsMessage
import ru.mirea.thud.client.service.CommonUnitActions.addToMap
import ru.mirea.thud.client.state.GameState
import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.common.model.messages.PlayerIdentifiers
import ru.mirea.thud.common.model.messages.ToServerMessages.DeleteFiguresMessage
import ru.mirea.thud.common.model.{FieldUnit, Location}

/*
Order of neighbors in list (numbers = indexes)
4  0  5
3  X  1
7  2  6
*/
object TrollService {

  private var cellsToHighlightAttack = new util.ArrayList[FieldUnit]
  private var cellsToHighlightMove = new util.ArrayList[FieldUnit]

  def getCellsToHighlightAttack: util.ArrayList[FieldUnit] = cellsToHighlightAttack

  def getCellsToHighlightMove: util.ArrayList[FieldUnit] = cellsToHighlightMove

  /*
    Calculate possible moves
  */
  def calculateTrollMovement(controllingUnit: FieldUnit): Unit = {
    cellsToHighlightAttack = checkForTrollsAttack(controllingUnit)
    cellsToHighlightMove = checkForTrollsMovement(controllingUnit)
    val map: Map[Location, CellTargetMode.Value] = addToMap(cellsToHighlightAttack, ATTACK)
    map ++ addToMap(cellsToHighlightMove, MOVE)
    fieldService ! HighlightCellsMessage(map)
  }

  /*
    Setting cells with dwarfs to empty and notify other player
  */
  def processTrollAttack(selectedCell: FieldUnit): Unit = {
    val dwarfsToKill = new util.ArrayList[FieldUnit]()
    for (neighbor <- selectedCell.neighbors if neighbor.cellType.equals(DWARF)) {
      neighbor.cellType = EMPTY
      dwarfsToKill.add(neighbor)
    }
    if (!dwarfsToKill.isEmpty) {
      fieldService ! DeleteFiguresMessage(PlayerIdentifiers(GameState.playerState.sessionId, GameState.playerState.id), dwarfsToKill)
    }
  }

  /*
     Check cells for attack possibility
       return ArrayList
  */
  private def checkForTrollsAttack(controllingUnit: FieldUnit): util.ArrayList[FieldUnit] = {
    val possibleCells = new util.ArrayList[FieldUnit]()
    (0 to 3).foreach {
      i => {
        var count = 0
        if (controllingUnit.neighbors.size >= i) {
          val neighbor = controllingUnit.neighbors(i)
          if (isCellTroll(neighbor) && ((i < 2 && controllingUnit.neighbors(i + 2).cellType.equals(TROLL)) || (i > 2 && controllingUnit.neighbors(i - 2).cellType.equals(TROLL)))) {
            count = countLineLength(neighbor, i)
          }
        }
        var index = i
        if (i < 2) {
          index = index + 2
        } else {
          index = index - 2
        }
        (0 to count).foreach {
          j => {
            if (controllingUnit.neighbors(index).cellType.equals(EMPTY)) {
              possibleCells add controllingUnit.neighbors(index)
            }
            possibleCells add controllingUnit.copy(controllingUnit.neighbors(index).location, controllingUnit.neighbors(index).cellType, controllingUnit.neighbors(index).neighbors)
          }
        }
      }
    }
    possibleCells
  }

  private def countLineLength(currentUnit: FieldUnit, index: Int): Int = {
    if (currentUnit.neighbors.size < index) return 0;
    var count = 0
    if (currentUnit.neighbors(index).cellType.equals(TROLL)) {
      count = countLineLength(currentUnit.neighbors(index), index + 1)
    }
    count + 1
  }

  /*
    Check cells for movement possibility
      return ArrayList
  */
  private def checkForTrollsMovement(controllingUnit: FieldUnit): util.ArrayList[FieldUnit] = {
    val possibleCells = new util.ArrayList[FieldUnit]()
    (4 to 7).foreach {
      i => {
        if (controllingUnit.neighbors.size > i) {
          val neighbor = controllingUnit.neighbors(i)
          if (isCellEmpty(neighbor)) {
            possibleCells.add(neighbor)
          }
        }
      }
    }
    possibleCells
  }
}
