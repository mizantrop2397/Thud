package ru.mirea.thud.client.service

import java.util

import ru.mirea.thud.client.app.ThudGame.fieldService
import ru.mirea.thud.client.constants.CellTargetMode.{ATTACK, MOVE}
import ru.mirea.thud.client.model.Cell
import ru.mirea.thud.client.model.messages.HighlightCellsMessage
import ru.mirea.thud.client.service.CommonUnitActions.addToMap
import ru.mirea.thud.common.constants.FieldCellType.{DWARF, EMPTY, TROLL}
import ru.mirea.thud.common.model.FieldUnit

/*
Order of neighbors in list (numbers = indexes)
4  0  5
3  X  1
7  2  6
*/
object DwarfService {
  private var cellsToHighlightAttack = new util.ArrayList[FieldUnit]
  private var cellsToHighlightMove = new util.ArrayList[FieldUnit]

  def getCellsToHighlightAttack: util.ArrayList[FieldUnit] = cellsToHighlightAttack

  def getCellsToHighlightMove: util.ArrayList[FieldUnit] = cellsToHighlightMove

  /**
    * Calculate possible moves
    */
  def calculateMovement(controllingUnit: FieldUnit): Unit = {
    cellsToHighlightAttack = checkForAttack(controllingUnit)
    cellsToHighlightMove = checkForMovement(controllingUnit)
    val map = addToMap(cellsToHighlightAttack, ATTACK)
    map ++ addToMap(cellsToHighlightMove, MOVE)
    fieldService ! HighlightCellsMessage(map)
  }

  /**
    * Setting cells with dwarfs to empty and notify other player
    */
  def processAttack(selectedCell: FieldUnit): Unit = {
    selectedCell.cellType match {
      case EMPTY => "что-то пошло не так"
      case DWARF => "а своих зачем бить?"
      case TROLL =>
        selectedCell.cellType = EMPTY
        "Всё хорошо"
    }
    // есть вопрос. В процессе атаки по идее еще и перемещается выбранный юнит
    // где это указывается?
  }

  /**
    * Check cells for attack possibility
    *
    * @return ArrayList
    */
  private def checkForAttack(controllingUnit: FieldUnit): util.ArrayList[FieldUnit] = {
    val possibleCells = new util.ArrayList[FieldUnit]()
    for (a <- 0 to 8) {
      val count = countLineLength(controllingUnit, a)
      val attackDirection = a match {
        case 0 => 2
        case 1 => 3
        case 2 => 0
        case 3 => 1
        case 4 => 6
        case 5 => 7
        case 6 => 4
        case 7 => 5
      }
      var cellToCheck = controllingUnit.neighbors(attackDirection)
      for (b <- 0 to count) {
        if (cellToCheck.cellType.equals(TROLL))
          possibleCells.add(cellToCheck)
        cellToCheck = cellToCheck.neighbors(attackDirection)
      }
    }
    possibleCells
  }

  private def countLineLength(currentUnit: FieldUnit, index: Int): Int = {
    var count = 0
    if (currentUnit.neighbors.size < index && currentUnit.neighbors(index).cellType.equals(DWARF)) {
      count = countLineLength(currentUnit.neighbors(count), index)
    }
    count + 1
  }

  /**
    * Check cells for movement possibility
    *
    * @return ArrayList
    */
  private def checkForMovement(controllingUnit: FieldUnit): util.ArrayList[FieldUnit] = {
    val possibleCells = new util.ArrayList[FieldUnit]()
    for (a <- 0 to 8) {
      var neighbor = controllingUnit.neighbors(a)
      while (Cell.isCellEmpty(neighbor)) {
        possibleCells.add(neighbor)
        neighbor = neighbor.neighbors(a)
      }
    }
    possibleCells
  }
}
