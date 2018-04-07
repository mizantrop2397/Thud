package ru.mirea.thud.server.generator

import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.common.model.{FieldUnit, GameField}
import ru.mirea.thud.server.calculator.NeighborsCalculator.calculateNeighbors
import ru.mirea.thud.server.constants.ServerConstants._

import scala.collection.mutable.ListBuffer

object GameFieldGenerator {
  def generateDefaultField(): GameField = {
    val field = generateFieldWithoutNeighbours
    field.units foreach { case (_, unit) => unit.neighbors = calculateNeighbors(unit, field) }
    field
  }

  private def generateFieldWithoutNeighbours: GameField = GameField(DEFAULT_UNIT_LOCATIONS map { case (id, location) => id -> FieldUnit(location, if (DEFAULT_CELL_TYPES contains id) DEFAULT_CELL_TYPES(id) else EMPTY, ListBuffer()) })
}
