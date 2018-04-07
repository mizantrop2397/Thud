package ru.mirea.thud.client.mapping

import ru.mirea.thud.client.constants.ClientConstants._

/**
  * Created by Anastasia on 06.04.2018.
  */
object UnitToCellMapping {
  def getUnitIdByImageView(id: String): Option[String] = IMAGE_VIEW_MAPPING find { case (_, value) => value == id } map { value => value._1 }

  def getUnitIdByRectangle(id: String): Option[String] = RECTANGLE_MAPPING find { case (_, value) => value == id } map { value => value._1 }

  def getRectangleId(unitId: String): String = RECTANGLE_MAPPING(unitId)

  def getImageViewId(unitId: String): String = IMAGE_VIEW_MAPPING(unitId)
}
