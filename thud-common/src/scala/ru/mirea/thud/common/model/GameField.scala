package ru.mirea.thud.common.model

import ru.mirea.thud.common.constants.FieldCellType

case class GameField(units: Map[Location, FieldCellType.Value] = Map())
