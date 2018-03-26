package ru.mirea.thud.client.model

import ru.mirea.thud.client.constants.FieldCellType
import ru.mirea.thud.client.constants.FieldCellType._
import ru.mirea.thud.common.model.Location

case class FieldUnit(location: Location, var cellType: FieldCellType.Value, neighbors: List[FieldUnit])

object Cell {
  def isCellEmpty(cell: FieldUnit): Boolean ={
    cell.cellType.equals(EMPTY)
  }

  def isCellOut(cell: FieldUnit): Boolean ={
    cell.cellType.equals(OUT)
  }

  def isCellDwarf(cell: FieldUnit): Boolean ={
    cell.cellType.equals(DWARF)
  }

  def isCellTroll(cell: FieldUnit): Boolean ={
    cell.cellType.equals(TROLL)
  }

  def isCellRock(cell: FieldUnit): Boolean ={
    cell.cellType.equals(ROCK)
  }
}
