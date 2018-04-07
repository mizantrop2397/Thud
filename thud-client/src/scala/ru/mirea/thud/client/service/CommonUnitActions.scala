package ru.mirea.thud.client.service

import ru.mirea.thud.client.app.ThudGame.fieldService
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.constants.CellTargetMode.DEFAULT
import ru.mirea.thud.client.model.messages.HighlightCellsMessage
import ru.mirea.thud.common.model.{FieldUnit, Location}

object CommonUnitActions {
  def setHighlightedCellsToDefault(arrayToAttack: List[FieldUnit], arrayToMove: List[FieldUnit]): Unit = {
    val map: Map[Location, CellTargetMode.Value] = addToMap(arrayToAttack, DEFAULT)
    map ++ addToMap(arrayToMove, DEFAULT)
    fieldService ! HighlightCellsMessage(map)
  }

  /*
 Convert ArrayList to Map [Location, CellTargetMode]
*/
  def addToMap(fieldUnits: List[FieldUnit], cellTargetMode: CellTargetMode.Value): Map[Location, CellTargetMode.Value] = fieldUnits map { unit => unit.location -> cellTargetMode } toMap
}
