package ru.mirea.thud.client.model.messages

import java.util

import ru.mirea.thud.client.model.FieldUnit

case class CalculateMovementSchemeMessage(controllingUnit: FieldUnit)

case class PerformMovementMessage(controllingUnit: FieldUnit, newCell: FieldUnit)

case class PerformAttackMessage(attackedUnit: FieldUnit)

case class DeleteFiguresMessage (cells: util.ArrayList[FieldUnit])