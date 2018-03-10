package ru.mirea.thud.client.model.messages

import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.model.Location

case class HighlightCellsMessage(cells: Map[Location, CellTargetMode.Value])