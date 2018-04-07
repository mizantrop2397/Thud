package ru.mirea.thud.client.service

import ru.mirea.thud.client.app.ThudGame.fieldService
import ru.mirea.thud.client.constants.CellTargetMode.{ATTACK, MOVE}
import ru.mirea.thud.client.model.Cell._
import ru.mirea.thud.client.model.messages.HighlightCellsMessage
import ru.mirea.thud.client.service.CommonUnitActions.addToMap
import ru.mirea.thud.common.constants.FieldCellType.{DWARF, EMPTY, TROLL}
import ru.mirea.thud.common.model.FieldUnit

import scala.collection.mutable.ListBuffer

object DwarfService {
  private var cellsToHighlightAttack = List[FieldUnit]()
  private var cellsToHighlightMove = List[FieldUnit]()

  def getDwarfCellsToHighlightAttack: List[FieldUnit] = cellsToHighlightAttack

  def getDwarfCellsToHighlightMove: List[FieldUnit] = cellsToHighlightMove

  def calculateDwarfMovement(controllingUnit: FieldUnit): Unit = {
    cellsToHighlightAttack = checkForAttack(controllingUnit)
    cellsToHighlightMove = checkForMovement(controllingUnit)
    val map = addToMap(cellsToHighlightAttack, ATTACK)
    map ++ addToMap(cellsToHighlightMove, MOVE)
    fieldService ! HighlightCellsMessage(map)
  }

  def processDwarfAttack(selectedCell: FieldUnit): Unit = {
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

  private def checkForAttack(controllingUnit: FieldUnit): List[FieldUnit] = {
    val possibleCells = ListBuffer[FieldUnit]()
    for (directionIndex <- 0 to 7) {
      val count = countLineLength(controllingUnit, directionIndex)
      val attackDirection = directionIndex match {
        case 0 => 2
        case 1 => 3
        case 2 => 0
        case 3 => 1
        case 4 => 6
        case 5 => 7
        case 6 => 4
        case 7 => 5
      }
      if (controllingUnit.neighbors.size >= attackDirection) {
        var cellToCheck = controllingUnit.neighbors(attackDirection)
        for (_ <- 0 to count) {
          if (cellToCheck.cellType.equals(TROLL)) possibleCells += cellToCheck
          cellToCheck = cellToCheck.neighbors(attackDirection)
        }
      }
    }
    possibleCells.toList
  }

  private def countLineLength(currentUnit: FieldUnit, directionIndex: Int): Int = {
    var count = 0
    val cellToCheck = currentUnit.neighbors(directionIndex)
    if (cellToCheck.cellType equals DWARF) {
      count = countLineLength(cellToCheck, directionIndex)
    }
    count + 1
  }

  private def checkForMovement(controllingUnit: FieldUnit): List[FieldUnit] = {
    val possibleCells = ListBuffer[FieldUnit]()
    for (directionIndex <- 0 to 7) {
      var neighbor = controllingUnit.neighbors(directionIndex)
      while (isCellEmpty(neighbor)) {
        possibleCells += neighbor
        neighbor = neighbor.neighbors(directionIndex)
      }
    }
    possibleCells.toList
  }
}
