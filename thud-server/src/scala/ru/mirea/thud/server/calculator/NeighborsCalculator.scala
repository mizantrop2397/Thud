package ru.mirea.thud.server.calculator

import ru.mirea.thud.common.constants.FieldCellType
import ru.mirea.thud.common.model.{FieldUnit, GameField, Location}

import scala.collection.mutable.ListBuffer

object NeighborsCalculator {
  def calculateNeighbors(unit: FieldUnit, gameField: GameField): ListBuffer[FieldUnit] = {
    val neighbors = ListBuffer[FieldUnit]()
    getNeighbor(unit.cellType, Location(unit.location.x + 1, unit.location.y), gameField) match {
      case Some(neighbor) => neighbors += neighbor
      case None =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x, unit.location.y + 1), gameField) match {
      case Some(neighbor) => neighbors += neighbor
      case None =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x + 1, unit.location.y + 1), gameField) match {
      case Some(neighbor) => neighbors += neighbor
      case None =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x + 1, unit.location.y - 1), gameField) match {
      case Some(neighbor) => neighbors += neighbor
      case None =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x - 1, unit.location.y - 1), gameField) match {
      case Some(neighbor) => neighbors += neighbor
      case None =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x - 1, unit.location.y + 1), gameField) match {
      case Some(neighbor) => neighbors += neighbor
      case None =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x - 1, unit.location.y), gameField) match {
      case Some(neighbor) => neighbors += neighbor
      case None =>
    }
    getNeighbor(unit.cellType, Location(unit.location.x, unit.location.y - 1), gameField) match {
      case Some(neighbor) => neighbors += neighbor
      case None =>
    }
    neighbors
  }

  private def getNeighbor(cellType: FieldCellType.Value, location: Location, gameField: GameField): Option[FieldUnit] = gameField
    .units find { case (_, fieldUnit) => fieldUnit.location == location } map { entry => entry._2 } match {
    case Some(unit) => if (unit.cellType == cellType) Some(unit) else None
    case None => None
  }
}
