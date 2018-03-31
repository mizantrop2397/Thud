package ru.mirea.thud.client.model.messages

import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.model.FieldUnit
import ru.mirea.thud.common.model.Location

case class HighlightCellsMessage(cells: Map[Location, CellTargetMode.Value])

case class MoveFiguresMessage (oldCell: FieldUnit, newCell: FieldUnit)
