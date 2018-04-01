package ru.mirea.thud.client.service

import java.util

import ru.mirea.thud.client.app.ThudGame.fieldController
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.constants.CellTargetMode.DEFAULT
import ru.mirea.thud.client.model.messages.HighlightCellsMessage
import ru.mirea.thud.common.model.{FieldUnit, Location}

object CommonUnitActions {

  def setHighlightedCellsToDefault(arrayToAttack: util.ArrayList[FieldUnit], arrayToMove: util.ArrayList[FieldUnit]): Unit = {
    val map: Map[Location, CellTargetMode.Value] = addToMap(arrayToAttack, DEFAULT)
    map ++ addToMap(arrayToMove, DEFAULT)
    fieldController ! HighlightCellsMessage(map)
  }

  /*
 Convert ArrayList to Map [Location, CellTargetMode]
*/
  def addToMap(fieldUnit: util.ArrayList[FieldUnit], cellTargetMode: CellTargetMode.Value): Map[Location, CellTargetMode.Value] = {
    var map: Map[Location, CellTargetMode.Value] = Map()
    for (i <- 0 to fieldUnit.size()) {
      map += (fieldUnit.get(i).location -> cellTargetMode)
    }
    map
  }
}
