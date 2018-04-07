package ru.mirea.thud.server.calculator

import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.common.model.{FieldUnit, GameField, Location}

import scala.collection.mutable.ListBuffer

/*
  Order of neighbors in list (numbers = indexes)
  4  0  5
  3  X  1
  7  2  6
*/
object NeighborsCalculator {
  def calculateNeighbors(unit: FieldUnit, gameField: GameField): ListBuffer[FieldUnit] = {
    val neighbors = ListBuffer[FieldUnit]()
    for (_ <- 0 to 7) neighbors += null
    getNeighbor(unit.cellType, Location(unit.location.x, unit.location.y + 1), gameField) match {
      case Some(neighbor) => neighbors.update(0, neighbor)
      case _ =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x + 1, unit.location.y), gameField) match {
      case Some(neighbor) => neighbors.update(1, neighbor)
      case _ =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x, unit.location.y - 1), gameField) match {
      case Some(neighbor) => neighbors.update(2, neighbor)
      case _ =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x - 1, unit.location.y), gameField) match {
      case Some(neighbor) => neighbors.update(3, neighbor)
      case _ =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x - 1, unit.location.y + 1), gameField) match {
      case Some(neighbor) => neighbors.update(4, neighbor)
      case _ =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x + 1, unit.location.y + 1), gameField) match {
      case Some(neighbor) => neighbors.update(5, neighbor)
      case _ =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x + 1, unit.location.y - 1), gameField) match {
      case Some(neighbor) => neighbors.update(6, neighbor)
      case _ =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x - 1, unit.location.y - 1), gameField) match {
      case Some(neighbor) => neighbors.update(7, neighbor)
      case _ =>
    }
    neighbors
  }

  private def getNeighbor(cellType: Value, location: Location, gameField: GameField): Option[FieldUnit] = gameField.units find { case (_, fieldUnit) => fieldUnit.location == location } map { entry => entry._2 }
}
