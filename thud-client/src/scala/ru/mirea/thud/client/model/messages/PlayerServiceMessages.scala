package ru.mirea.thud.client.model.messages

import ru.mirea.thud.common.model.FieldUnit

case class CalculateMovementSchemeMessage(controllingUnit: FieldUnit)

case class PerformMovementMessage(controllingUnit: FieldUnit, newCell: FieldUnit)

case class PerformAttackMessage(attackedUnit: FieldUnit)