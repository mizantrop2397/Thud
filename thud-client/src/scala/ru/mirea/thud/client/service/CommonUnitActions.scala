package ru.mirea.thud.client.service

import java.util

import ru.mirea.thud.client.app.ThudGame.fieldService
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.constants.CellTargetMode.DEFAULT
import ru.mirea.thud.client.model.messages.HighlightCellsMessage
import ru.mirea.thud.common.model.{FieldUnit, Location}

import scala.collection.JavaConverters.iterableAsScalaIterableConverter

object CommonUnitActions {
  def setHighlightedCellsToDefault(arrayToAttack: util.ArrayList[FieldUnit], arrayToMove: util.ArrayList[FieldUnit]): Unit = {
    val map: Map[Location, CellTargetMode.Value] = addToMap(arrayToAttack, DEFAULT)
    map ++ addToMap(arrayToMove, DEFAULT)
    fieldService ! HighlightCellsMessage(map)
  }

  /*
 Convert ArrayList to Map [Location, CellTargetMode]
*/
  def addToMap(fieldUnit: util.ArrayList[FieldUnit], cellTargetMode: CellTargetMode.Value): Map[Location, CellTargetMode.Value] = fieldUnit.asScala map { unit => unit.location -> cellTargetMode } toMap
}
