package ru.mirea.thud.server.calculator

import akka.japi.Option.Some
import ru.mirea.thud.common.constants.FieldCellType.EMPTY
import ru.mirea.thud.common.model.{FieldUnit, Location}
import ru.mirea.thud.server.app.Server.gameField

object NeighborsCalculator {
  def calculateNeighbors(unit: FieldUnit): List[FieldUnit] = {
    val neighbors = List[FieldUnit]()
    getNeighbor(unit.location.x + 1, unit.location.y).fold()(neighbors.::)
    getNeighbor(unit.location.x - 1, unit.location.y).fold()(neighbors.::)
    getNeighbor(unit.location.x, unit.location.y + 1).fold()(neighbors.::)
    getNeighbor(unit.location.x, unit.location.y - 1).fold()(neighbors.::)
    getNeighbor(unit.location.x + 1, unit.location.y + 1).fold()(neighbors.::)
    getNeighbor(unit.location.x - 1, unit.location.y - 1).fold()(neighbors.::)
    getNeighbor(unit.location.x + 1, unit.location.y - 1).fold()(neighbors.::)
    getNeighbor(unit.location.x - 1, unit.location.y - 1).fold()(neighbors.::)
    neighbors
  }

  private def getNeighbor(x: Int, y: Int): Option[FieldUnit] = gameField.units(Location(x, y)) match {
    case fieldUnit % FieldUnit(_, cellType, _) => if (cellType != EMPTY) Some(fieldUnit) else None(fieldUnit)
  }

}
