package ru.mirea.thud.common.model

import ru.mirea.thud.common.constants.FieldCellType

import scala.collection.mutable.ListBuffer

case class FieldUnit(location: Location, var cellType: FieldCellType.Value, neighbors: ListBuffer[FieldUnit])
